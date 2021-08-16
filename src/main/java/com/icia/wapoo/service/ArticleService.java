package com.icia.wapoo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.wapoo.dao.ArticleDao;
import com.icia.wapoo.model.ArticleFQA;
import com.icia.wapoo.model.ArticleNotice;
import com.icia.wapoo.model.ArticleQueAn;


@Service("ArticleService")
public class ArticleService {

	private static Logger logger = LoggerFactory.getLogger(ArticleService.class);
	
	@Autowired(required=false)
	private ArticleDao articleDao;
	
	//공지사항 총 수
	public long noticeListCount(ArticleNotice articleNotice)
	{
		
		long count= 0;
		
		try
		{
			count = articleDao.NoticeListCount(articleNotice);
		}
		catch(Exception e)
		{
		
			logger.error("[ArticleService] ArticleService Exception", e);
		}
		
		
		return count;
	}
	
	
	//공지사항 리스트
	public List<ArticleNotice> noticeList(ArticleNotice articleNotice)
	{
		//리스트
		List<ArticleNotice> list = null;
		
		try
		{
			list = articleDao.NoticeList(articleNotice);
		}
		catch(Exception e)
		{
	
			logger.error("[ArticleService] NoticeList Exception", e);
		}
		
		return list;
	}
	
	//Q&A 총 수
	public long queAnListCount(ArticleQueAn articlaQueAn)
	{
		long count = 0;
		
		try
		{
			count = articleDao.QueAnListCount(articlaQueAn);
		}
		catch(Exception e)
		{
			
			logger.error("[ArticleService] noticeList Exception", e);
		}
		
		return count;
	}
	
	
	//Q&A 리스트
	public List<ArticleNotice> queAnList(ArticleQueAn articlaQueAn)
	{
		//리스트
		List<ArticleNotice> list = null;
		
		try
		{
			list = articleDao.QueAnList(articlaQueAn);
		}
		catch(Exception e)
		{
			
			logger.error("[ArticleService] queAnList Exception", e);
		}
		
		return list;
	}
	
	
	//FQA 총 수
	public long frequentlyquestionListCount(ArticleFQA articleFQA)
	{
		//리스트
		long count = 0;
		
		try
		{
			count = articleDao.FQAListCount(articleFQA);
		}
		catch(Exception e)
		{
			
			logger.error("[ArticleService] frequentlyquestionListCount Exception", e);
		}
		
		return count;
	}
	
	//FQA 리스트
	public List<ArticleNotice> frequentlyquestionList(ArticleFQA articleFQA)
	{
		List<ArticleNotice> list = null;
		
		try
		{
			list = articleDao.FQAList(articleFQA);
		}
		catch(Exception e)
		{
			
			logger.error("[ArticleService] queAnList Exception", e);
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}

