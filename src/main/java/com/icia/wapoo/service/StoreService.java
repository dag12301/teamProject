package com.icia.wapoo.service;

import com.icia.wapoo.S3.S3Service;
import com.icia.wapoo.dao.StoreDao;
import com.icia.wapoo.model.Store;
import com.icia.wapoo.model.StoreFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StoreService {
    @Autowired
    private final S3Service s3Service;
    @Autowired
    private final StoreDao storeDao;

    @Transactional
    public int registerStore(Store store, List<MultipartFile> files){
        int result = storeDao.insertStore(store);
        System.out.println("StoreService.registerStore : 적용된 게시물수 => " + result);
        System.out.println("StoreService.registerStore : 새로생긴 가게 storeId => " + store.getStoreId());
        if ( result > 0) {

            for( MultipartFile file : files) {

                String fileURL = null;
                try {
                    fileURL = s3Service.upload(file, "store_"+store.getStoreId());
                } catch (IOException e) {
                    throw new RuntimeException("S3 업로드중 오류발생!");
                }
                StoreFile storeFile = new StoreFile();
                storeFile.setStore_id(store.getStoreId());
                storeFile.setFilesize(file.getSize());
                storeFile.setFiletype(file.getContentType());
                storeFile.setName(fileURL);
                storeFile.setOrgName(file.getName());
                storeDao.insertStoreFile(storeFile);
            }
            return result;

        } else {
            throw new RuntimeException("가게 정보 삽입중에 오류발생!");
        }
    }

    public List<Map<String, Object>> getStoreList(int listPerPage, int requestPage, String option) {
        int StartLimit = (requestPage-1)* listPerPage;
        int EndLimit = listPerPage;
        System.out.println((StartLimit+1) + "번째 부터 시작하여 "+EndLimit +"개를 가져옵니다.");
        List<Map<String, Object>> list = storeDao.selectStoreList(StartLimit, EndLimit, option);
        System.out.println("가져온 게시물 수 : " + list.size());
        return list;
    }

    public int getStoreListCount(String option) {
        return storeDao.selectStoreListCount(option);
    }

    public void updateStoreStatus(int storeId, String status) {
        storeDao.updateStoreStatus(storeId, status);
    }

    public Store getStoreById(int memberId) {
        List<Store> stores = storeDao.selectStoreById(memberId);
        // 가게가 여러개 있는일은 없어야하는데, 일단은 최상위 1개만 보내도록 하자.
        if(stores.size() > 0){
            // 결과가 있을때만 꺼내서 줌.
            return stores.get(0);
        }
        return new Store();
    }
}
