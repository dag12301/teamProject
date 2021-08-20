package com.icia.wapoo.dao;

import com.icia.wapoo.model.Store;
import com.icia.wapoo.model.StoreFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StoreDao {
    int insertStoreFile(StoreFile storeFile);

    int insertStore(Store store);

    List<Map<String, Object>> selectStoreList(@Param("startLimit") int startLimit, @Param("endLimit") int endLimit);

    int selectStoreListCount();
}
