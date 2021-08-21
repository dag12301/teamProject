package com.icia.wapoo.dao;


import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Event;


@Repository
@Mapper
public interface EventDao {
	
    int insertEvent(Event event);

}
