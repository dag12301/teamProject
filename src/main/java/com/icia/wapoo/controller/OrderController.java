package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Food;
import com.icia.wapoo.service.AkinatorService;
import com.icia.wapoo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final StoreService storeService;

    private int getMemberIdByRequest(HttpServletRequest request) {
        System.out.println("받은 토큰으로 멤버를 검색합니다");
        String token = jwtService.resolveToken(request);
        Map<String, Object> claims = jwtService.getUserInfo(token);
        return ((Integer) claims.get("memberId")).intValue();
    }

    @PostMapping("/getFoodList")
    public ResponseEntity getFoodList(@RequestBody Map<String, Object> data) {
        List<Integer> foodIdList = (List<Integer>) data.get("foodIdList");
        List<Map<String, Object>> foodList = storeService.getFoodListByFoodIdList(foodIdList);
        return new ResponseEntity(foodList, HttpStatus.OK);
    }
}
