package com.icia.wapoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icia.wapoo.dao.EventDao;

import com.icia.wapoo.model.Event;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {
   
    @Autowired
    private final EventDao eventDao;

    @Transactional
    public int insertEvent(Event event){
    	System.out.println("서비스 들어옴");
    	int count = eventDao.insertEvent(event);
        System.out.println("EventService : 적용된 이벤트 수 => " + count);
        
        if ( count > 0) {
        	System.out.println("EventService 통과");
        	return count;
        	
        } else {
        	System.out.println("EventService 통과못함");
            throw new RuntimeException("가게 정보 삽입중에 오류발생!");
        }
    }
}
