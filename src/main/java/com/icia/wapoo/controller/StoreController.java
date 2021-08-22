package com.icia.wapoo.controller;

import com.icia.wapoo.S3.S3Service;
import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Food;
import com.icia.wapoo.model.Store;
import com.icia.wapoo.service.AkinatorService;
import com.icia.wapoo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
@Slf4j
public class StoreController {
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final StoreService storeService;
    @Autowired
    private final AkinatorService akinatorService;


    @PostMapping("/addstore")
    public ResponseEntity addStore(Store store
            , @RequestPart(value = "fileList", required = false) List<MultipartFile> files, HttpServletRequest request){

        String token = jwtService.resolveToken(request);
        System.out.println(token);
        Map<String, Object> claims = jwtService.getUserInfo(token);
        System.out.println("클레임있나여" +claims);
        store.setOwner_id(((Integer) claims.get("memberId")).intValue());
        int result = storeService.registerStore(store, files);
        System.out.println("결과값은?" + result);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @PostMapping("/findStore")
    public ResponseEntity findStore(HttpServletRequest request) {
        try{
            String token = jwtService.resolveToken(request);
            Map<String, Object> claims = jwtService.getUserInfo(token);
            Integer memberId = ((Integer) claims.get("memberId")).intValue();
            Store store = null;
            if( memberId != null ){
                System.out.println("허용된 사용자, 가게를 찾아봅니다.");
                store = storeService.getStoreById(memberId);
            }
            return new ResponseEntity(store, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("허용할수없는 토큰");
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/getAkinatorList")
    public ResponseEntity getAkinatorList() {
        List<Map<String,Object>> akinatorList = akinatorService.getAkinatorList();
        return new ResponseEntity(akinatorList, HttpStatus.OK);
    }

    @PostMapping("/addFood")
    public ResponseEntity addFood(
            Food food
            ,@RequestPart(value = "file", required = false) MultipartFile file, HttpServletRequest request
    ) {
        try{
            String token = jwtService.resolveToken(request);
            Map<String, Object> claims = jwtService.getUserInfo(token);
            Integer memberId = ((Integer) claims.get("memberId")).intValue();
            Store store = null;
            if( memberId != null ){
                store = storeService.getStoreById(memberId);

                if(store == null) {
                    //가게가 없다?
                    System.out.println("가게가 없다구요?");
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(store.getStatus().equals("Y")){
                    // 가게가 영업승인이 났을 때,
                    food.setStore_id(store.getStoreId());
                    System.out.println(food);
                    int foodId = storeService.addFood(food, file);
                    if(foodId > 0 ){
                        System.out.println("새로생긴 FoodId를 성공적으로 넘겨줍니다 : "+ foodId);
                        return new ResponseEntity(foodId, HttpStatus.OK);
                    }
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                // 가게가 영업승인이 안났어..
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            // 멤버아이디가 없을때?
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            System.out.println("허용할수없는 토큰");
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/setAkinator")
    public ResponseEntity getStoreList(@RequestBody Map<String, Object> data) {
        Map<String, Object> akinator = (Map<String, Object>) data.get("akinator");
        int foodId = ((Integer) data.get("foodId")).intValue();
        int result = akinatorService.addAkinator(akinator, foodId);
        if(result == 1){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
