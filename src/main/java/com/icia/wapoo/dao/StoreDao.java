package com.icia.wapoo.dao;

import com.icia.wapoo.model.Store;
import com.icia.wapoo.model.StoreFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StoreDao {
    int insertStoreFile(StoreFile storeFile);

    int insertStore(Store store);
}
