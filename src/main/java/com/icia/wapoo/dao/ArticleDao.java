package com.icia.wapoo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.ArticleFQA;
import com.icia.wapoo.model.ArticleNotice;
import com.icia.wapoo.model.ArticleQueAn;

@Repository("ArticleDao")
public interface ArticleDao {

	//공지사항 리스트 총 수
	public long NoticeListCount(ArticleNotice articleNotice);
	//공지사항 리스트
	public List<ArticleNotice> NoticeList(ArticleNotice articleNotice);
	
	// Q&A 리스트 총 수 
	public long QueAnListCount(ArticleQueAn articleQueAn);
	// Q&A 리스트
	public List<ArticleNotice> QueAnList(ArticleQueAn articleQueAn);
	
	//FQA 리스트 총수
	public long FQAListCount(ArticleFQA articleFQA);
	//FQA 리스트
	public List<ArticleNotice> FQAList(ArticleFQA articleFQA);
}
