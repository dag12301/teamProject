package com.icia.wapoo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Event;
import com.icia.wapoo.service.AdminService;
import com.icia.wapoo.service.EventService;
import com.icia.wapoo.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
    @Autowired
    private final EventService eventService;
    @Autowired
    private final JwtService jwtService;

    @PostMapping("/eventinsert")
    public void getStoreList(@RequestBody Map<String, Object> data) {
        String title = ((String) data.get("title")).toString();
        String body = ((String) data.get("body")).toString();
        String dueDate = ((String) data.get("dueDate")).toString();
        if(!title.isEmpty() && !body.isEmpty() && !dueDate.isEmpty()){
            Event event = new Event();
            event.setTitle(title);
            event.setBody(body);
            event.setDueDate(dueDate);
            
            System.out.println("쿠폰에 데이터가 들어갔습니다.");
            
        }
        
    }
}
