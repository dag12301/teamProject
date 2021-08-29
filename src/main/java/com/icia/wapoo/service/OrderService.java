package com.icia.wapoo.service;

import com.icia.wapoo.dao.OrderDao;
import com.icia.wapoo.model.KakaoPayApproval;
import com.icia.wapoo.model.KakaoPayReady;
import com.icia.wapoo.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderDao orderDao;

    // 카카오페이 관련
    private static String KAKAOKEY;

    private static final String HOST = "https://kapi.kakao.com";
    private KakaoPayReady kakaoPayReadyVO;
    private KakaoPayApproval kakaoPayApprovalVO;
    private Integer orderId;
    private List<Integer> foodList;
    private String storeName = "wapooStore";
    private int totalQuantity;
    private int totalPrice;

    @Value("${kakao.key}")
    public void setKakaokey(String key) {
        KAKAOKEY = key;
    }

    public Integer createOrder(Map<String, Object> orderData) {
        // 쿠폰 정보확인 및 디스카운트 초기화
        int discount = 0;
        orderData.put("discount", 0);
        // 총 수량 초기화
        totalQuantity = 0;
        // 총 가격 초기화
        totalPrice = -discount;
        int result = orderDao.insertOrder(orderData);
        if(result > 0 ){
            orderId = ((BigInteger) orderData.get("orderId")).intValue();
            if (orderId!=null || orderId > 0) {
                return orderId;
            }
            return 0;
        }
        return 0;
    }

    public int createOrderInfo(Integer orderId, Integer quantity, Integer foodId) {
        foodList = new ArrayList<>();
        if(this.orderId == orderId) {
            int result = orderDao.insertOrderInfo(this.orderId, quantity, foodId);
            System.out.println(
                    "orderId : " +orderId + " 에 foodId : "+foodId+ " 가" + quantity+"개 포함됬습니다");
            if(result > 0) {
                // foodId로 storeId 얻기
                foodList.add(foodId);
                totalQuantity += quantity;
                totalPrice += orderDao.selectPriceByFoodId(foodId);
                return result;
            }
            return 0;
        }
        return 0;
    }

    public String getKakaoPayReady() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KAKAOKEY);
        System.out.println("카카오키 : "+ KAKAOKEY );
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content_Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        System.out.println("오더아아디 : "+orderId.toString());
        params.add("partner_order_id", orderId.toString());
        // orderId로 가게아이디 구해와야하지만 테스트이므로 고정값
        System.out.println("스토어 네임 : "+storeName);
        params.add("partner_user_id", storeName);
        // 아이템리스트
        System.out.println("아이템리스트 : "+ foodList.toString());
        params.add("item_name", foodList.toString());
        System.out.println("총수량 : "+ String.valueOf(totalQuantity));
        params.add("quantity", String.valueOf(totalQuantity));
        System.out.println("총가격 : "+ String.valueOf(totalPrice));
        params.add("total_amount", String.valueOf(totalPrice));
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReady.class);


            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "BAD";
    }

    public int getkakaoPayInfo(String pg_token) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("카카오페이 결제정보를 불러옵니다.");
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KAKAOKEY);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", orderId.toString());
        params.add("partner_user_id", storeName);
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(totalPrice));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApproval.class);
            Payment payment = new Payment();
            payment.setOrder_id(Integer.parseInt(kakaoPayApprovalVO.getPartner_order_id()));
            payment.setPayAmount(kakaoPayApprovalVO.getAmount().getTotal());
            payment.setPayMethod(kakaoPayApprovalVO.getPayment_method_type());
            payment.setAid(kakaoPayApprovalVO.getAid());
            payment.setTid(kakaoPayApprovalVO.getTid());
            System.out.println(payment);
            orderDao.insertPayment(payment);

            int result = orderDao.updateOrderState(payment.getOrder_id(), "Y");
            System.out.println("적용된 order 레코드 수 : "+ result);
            result = orderDao.updateOrderInfosStatus(payment.getOrder_id(), "Y");
            System.out.println("적용된 orderInfo 레코드 수 : " + result);
            return payment.getOrder_id();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    public Map<String, Object> getOrderedFoodList(Integer orderId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("order",orderDao.selectOrderByOrderId(orderId));
        List<Map<String, Object>> orderedFoodsList  = orderDao.selectAllOrderedFoodByOrderId(orderId);
        resultMap.put("foodList", orderedFoodsList);
        return resultMap;
    }
}
