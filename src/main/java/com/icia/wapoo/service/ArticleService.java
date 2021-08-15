package com.icia.wapoo.service;

import com.icia.wapoo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.wapoo.dao.ArticleDao;

import java.util.List;


@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	//게시글 총 수
	public List<Article> listAllByBoard(int boardId) {
		List<Article> list = articleDao.selectAllByBoardId(boardId);
		list.stream().forEach(System.out::println);
		return list;
	}
		
}

