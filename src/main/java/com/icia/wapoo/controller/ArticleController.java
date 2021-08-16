package com.icia.wapoo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icia.wapoo.model.ArticleFQA;
import com.icia.wapoo.model.ArticleNotice;
import com.icia.wapoo.model.ArticleQueAn;
import com.icia.wapoo.service.ArticleService;

@RestController
public class ArticleController {

	@Autowired
    private ArticleService articleService;
	
	
	//공지사항리스트
    @GetMapping("/serviceCenter/notice")
    public ResponseEntity notice(ModelMap model,HttpServletRequest request,HttpServletResponse response)
    {
    	//공지사항 리스트
    	ArticleNotice articleNotice = new ArticleNotice();
    	//리스트
    	List<ArticleNotice> list = null;
    	
    	long totalCount = 0;
    	
    	System.out.println("totalCount: " + totalCount);
    	//공지사항 총 수 
    	totalCount = articleService.noticeListCount(articleNotice);
    	
    	if(totalCount > 0)
    	{
    		list = articleService.noticeList(articleNotice);
    		
    		model.addAttribute("list", list);
    		
    		return new ResponseEntity("성공적으로 발행", HttpStatus.OK);
    	}
    	
    	
    	return new ResponseEntity("비정상 발행", HttpStatus.OK);
    }
	
	
    //Q&A 리스트
    @GetMapping("/q&a")
    public ResponseEntity QuestionAnswer(ModelMap model,HttpServletRequest request,HttpServletResponse response)
    {
    	//Q&A 리스트
    	ArticleQueAn articleNotice = new ArticleQueAn();
    	//리스트
    	List<ArticleNotice> list = null;
    	//Q&A 총 수
    	long totalCount = 0;
    	
    	totalCount = articleService.queAnListCount(articleNotice);
    	
    	
    	if(totalCount > 0)
    	{
    		list = articleService.queAnList(articleNotice);
    		
    		model.addAttribute("list", list);
    		
    		return new ResponseEntity("성공적으로 발행", HttpStatus.OK);
    	}
    	
    	return new ResponseEntity("비정상 발행", HttpStatus.OK);
    }
    
    
  //FQA 리스트
    @GetMapping("/fqa")
    public ResponseEntity frequentlyquestion(ModelMap model,HttpServletRequest request,HttpServletResponse response)
    {
    	//Q&A 리스트
    	ArticleFQA articleFQA = new ArticleFQA();
    	//리스트
    	List<ArticleNotice> list = null;
    	//Q&A 총 수
    	long totalCount = 0;
    	
    	totalCount = articleService.frequentlyquestionListCount(articleFQA);
    	
    	if(totalCount > 0)
    	{
    		list = articleService.frequentlyquestionList(articleFQA);
    		
    		model.addAttribute("list", list);
    		
    		return new ResponseEntity("성공적으로 발행", HttpStatus.OK);
    	}
    	
    	
    	return new ResponseEntity("비정상 발행", HttpStatus.OK);
    }
    
    
    
    
    
}


    
    
    
    
    

