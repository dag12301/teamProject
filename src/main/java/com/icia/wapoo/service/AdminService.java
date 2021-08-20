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

    public List<Map<String, Object>> getStoreList(int listPerPage, int currentPage) {
        return storeService.getStoreList(listPerPage, currentPage);
    }

    public int getStoreListCount() {
        return storeService.getStoreListCount();
    }
}
