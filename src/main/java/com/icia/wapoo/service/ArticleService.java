package com.icia.wapoo.service;

import com.icia.wapoo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.wapoo.dao.ArticleDao;

import java.util.List;
import java.util.Map;


@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	//게시글 총 수
	public List<Article> listAllByBoard(int boardId) 
	{
		List<Article> list = articleDao.selectAllByBoardId(boardId);
		list.stream().forEach(System.out::println);
		return list;
	}
	
	//게시글 등록
	public int listInsert(Article article)
	{
		int count = 0;
		
		try
		{
			count = articleDao.boardInsert(article);
		}
		catch(Exception e)
		{
			System.out.println("오류: "+ e);
		}
		
		return count;
	}
	
	//게시글 삭제
	public long boardDelete(long articleId)
	{
		long count = 0;
		System.out.println("55555555555555555555555555");
		Article article = articleDao.boardSelect(articleId);
		System.out.println("6666666666666666666666666666666");
		if(article != null)
		{
			System.out.println("7777777777777777777777777");
			count = articleDao.boardDelete(articleId);
			
			
			if(count > 0)
			{
				//파일등 다른 작업 필요시 여기서 함
			}
			
		}

		return count;
	}
		
}

