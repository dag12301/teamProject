package com.icia.wapoo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Event;
import com.icia.wapoo.model.Store;
import com.icia.wapoo.model.StoreFile;

@Repository
@Mapper
public interface EventDao {
	
    int insertEvent(Event event);

}
