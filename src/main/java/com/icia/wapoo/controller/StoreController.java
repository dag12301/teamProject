package com.icia.wapoo.controller;

import com.icia.wapoo.S3.S3Service;
import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Store;
import com.icia.wapoo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
@Slf4j
public class StoreController {

    private final JwtService jwtService;

    private final StoreService storeService;


    @PutMapping("/addstore")
    public ResponseEntity addStore(Store store
            , @RequestPart(value = "fileList", required = false) List<MultipartFile> files, HttpServletRequest request){
        // 스토어에 오너ID 세팅
//        //JWT 토큰값
//        String JWT = jwtService.resolveToken(request);
//        Map<String, Object> token = jwtService.getUserInfo(JWT);
//        // token에서 memberId 값 가져오기
//        int ownerId = (int) token.get("memberId");


        store.setOwner_id(1);
        int result = storeService.registerStore(store, files);

        return new ResponseEntity(result, HttpStatus.OK);
    }
}
