package com.icia.wapoo.controller;

import com.icia.wapoo.service.AkinatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/akinator")
@RequiredArgsConstructor
public class AkinatorController {

    @Autowired
    AkinatorService akinatorService;

    @GetMapping("/getAkinators")
    public ResponseEntity getAkinators() {
        List<Map<String, Object>> list = akinatorService.getAkinatorList();

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/getFoodList")
    public ResponseEntity getFoodList(@RequestBody List<Map<String, Object>> answers) {
        List<Map<String, Object>> foodList = akinatorService.getFoodList(answers);
        return new ResponseEntity(foodList, HttpStatus.OK);
    }

}
