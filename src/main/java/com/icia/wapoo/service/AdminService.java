package com.icia.wapoo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("adminService")
public class AdminService {
    @Autowired
    private final StoreService storeService;

    public List<Map<String, Object>> getStoreList(int listPerPage, int currentPage, String option) {
        return storeService.getStoreList(listPerPage, currentPage, option);
    }

    public int getStoreListCount(String option) {
        return storeService.getStoreListCount(option);
    }

    public void updateStoreStatus(int storeId, String status) {
        System.out.println(storeId+ " 가게의 상태를 "+status+" 로 바꿉니다.");
        storeService.updateStoreStatus(storeId, status);
    }
}
