package com.icia.wapoo.controller;

import com.icia.wapoo.dao.LoginInfoDao;
import com.icia.wapoo.dao.StoreDao;
import com.icia.wapoo.model.ImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {

    @Autowired
    LoginInfoDao loginInfoDao;
    @Autowired
    StoreDao storeDao;

    @GetMapping("/api/test")
    public String test() {
        List<ImageFile> imageFile = storeDao.selectStoreFileList(1);
        imageFile.stream().forEach(System.out::println);
        return "OK";
    }
}
