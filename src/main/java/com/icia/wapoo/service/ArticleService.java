package com.icia.wapoo.service;

import com.icia.wapoo.model.Article;
import com.icia.wapoo.paging.PagingA;

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
	public List<Article> listAllByBoard(long boardId) 
	{
		List<Article> list = articleDao.selectAllByBoardId(boardId);
		list.stream().forEach(System.out::println);
		return list;
	}
	
	//게시글 조회
	public Article boardList(long articleId) 
	{
		Article article = null;
		
		try 
		{
			article = articleDao.boardList(articleId);
		}
		catch(Exception e)
		{
			System.out.println();
			System.out.println(e);
		}
		
		System.out.println(article);
		
		
		return article;
	}
	
	//조회수 증가
	public int boardHit(long articleId)
	{
		int count = 0;
		
		try 
		{
			
			count = articleDao.boardHit(articleId);	
			
		}
		catch(Exception e)
		{
			
			System.out.println();
			System.out.println(e);
		}
		
		return count;
	}
	
	//게시글 등록
	public int listInsert(Map<String, Object> writeData, long writerId)
	{
		
		int count = 0;
		
		Article article = new Article();
		
		
		article.setTitle((String)writeData.get("title"));

		article.setBody((String)writeData.get("body"));
		
		article.setStatus((String)writeData.get("status"));
		
		
		
		article.setBoardId( Integer.parseInt((String)writeData.get("boardId")));
		
		

		
		article.setWriterId(writerId);
		
		try
		{
			
			count = articleDao.boardInsert(article);
			article.getArticleId();
			
			if(count > 0)
			{
				System.out.println("데이터 DB 저장");
			}
			
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
		
		Article article = articleDao.boardSelect(articleId);
		
		if(article != null)
		{
			
			count = articleDao.boardDelete(articleId);
			
			
			if(count > 0)
			{
				//파일등 다른 작업 필요시 여기서 함
			}
			
		}

		return count;
	}
	
	
	//총게시글 수
	public int getBoardListCnt(long boardId) throws Exception {
		
		int count = 0;
		
		count = articleDao.getBoardListCnt(boardId);
		
		return count;

	}

	//페이징 번호 맞게 출력
	public List<Article> getBoardList(PagingA paging, long boardId) throws Exception {

		List<Article> list = articleDao.getBoardList(paging, boardId);
		
		
		
		return list;

	}



	
	
		
}

