package com.icia.wapoo.controller;

import com.icia.wapoo.model.Member;
import com.icia.wapoo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    public MemberService memberService;

    @Autowired
    public TestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/test")
    public Map<String, Object> testMVC() {
        Map<String, Object> map = new HashMap<>();

        List<Member> list = memberService.getMembers();
        if(list.size() == 0){
            map.put("연결실패", list);
            return map;
        }

        map.put(list.get(0).getName(), list);

        return map;
    }
}
