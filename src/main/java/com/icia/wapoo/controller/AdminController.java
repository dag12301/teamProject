package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Store;
import com.icia.wapoo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final AdminService adminService;
    @Autowired
    private final JwtService jwtService;

    @PostMapping("/getStoreList")
    public ResponseEntity getStoreList(@RequestBody Map<String, Object> data) {
        int listPerPage = ((Integer) data.get("listPerPage")).intValue();
        int currentPage = ((Integer) data.get("currentPage")).intValue();
        if(listPerPage <= 0 || currentPage <=0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        System.out.println("요청페이지 : " + currentPage + ", 요청게시물 수 : "+listPerPage);
        // 가게페이지를 가져옵니다.

        List<Map<String, Object>> result = adminService.getStoreList(listPerPage, currentPage);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @GetMapping("/getStoreListCount")
    public ResponseEntity getStoreListCount() {
        int result = adminService.getStoreListCount();
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
