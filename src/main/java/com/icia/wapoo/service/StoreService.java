package com.icia.wapoo.service;

import com.icia.wapoo.S3.S3Service;
import com.icia.wapoo.dao.StoreDao;
import com.icia.wapoo.model.Store;
import com.icia.wapoo.model.StoreFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final S3Service s3Service;

    private final StoreDao storeDao;

    @Transactional
    public int registerStore(Store store, List<MultipartFile> files){
        int result = storeDao.insertStore(store);
        if ( result > 0) {
            System.out.println("StoreService.registerStore : 생성된 StoreId => " + result);
            for( MultipartFile file : files) {

                String fileURL = null;
                try {
                    fileURL = s3Service.upload(file, "store_"+result);
                } catch (IOException e) {
                    throw new RuntimeException("S3 업로드중 오류발생!");
                }
                StoreFile storeFile = new StoreFile();
                storeFile.setStore_id(result);
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

}
