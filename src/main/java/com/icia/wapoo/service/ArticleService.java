package com.icia.wapoo.service;

import com.icia.wapoo.model.Article;
import com.icia.wapoo.model.ImageFile;
import com.icia.wapoo.model.Store;
import com.icia.wapoo.paging.PagingA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.icia.wapoo.S3.S3Service;
import com.icia.wapoo.dao.ArticleDao;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class ArticleService {

	@Autowired
	private final S3Service s3Service = null;
	
	@Autowired
	private ArticleDao articleDao;
	
	//이미지 등록
    public int registerStore(ImageFile file){
        System.out.println("registerStore 실행 " );
        
        int count = 0;
        
        try
        {
        	count = articleDao.insertArticleFile(file);				//파일 DB실행함
        }
        catch (Exception e) 
        {
            throw new RuntimeException("registerStore 오류발생!" + e);
        }
        
		
        return count;
        
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
	
	//이미지 조회
	public List<ImageFile> imageFileList(long articleId)
	{
		List<ImageFile> articleImageFile = null;
		
		try
		{
			articleImageFile = articleDao.imageFileList(articleId);
		}
		catch(Exception e)
		{
			System.out.println("imageFileList 오류: " + e);
		}
		return articleImageFile;
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
			System.out.println("boardHit 오류 : "+ e);
		}
		
		return count;
	}
	
	//게시글 등록
	public int listInsert(Article article, long writerId)
	{
		
		int count = 0;
		
		try
		{
			
			count = articleDao.boardInsert(article);
			
			
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
	
	//댓글 등록
	public int commentInsert(Map<String, Object> params, long memberId)
	{
		int count = 0;
		
		Article article = new Article();
		
		article.setTitle((String)params.get("title"));
		
		article.setParantId( (long)(((Integer) params.get("parantId")).intValue()));
		
		article.setWriterId(memberId);
		
		try
		{
			count = articleDao.commentInsert(article);
		}
		catch(Exception e)
		{
			System.out.println("commentInsert DB오류"+ e);
		}
		
		return count;
	}
	
	//게시글 댓글 수 증가
	public int childrenHit(long articleId)
	{
		int count = 0;
		
		try 
		{
			count = articleDao.childrenHit(articleId);	
			
		}
		catch(Exception e)
		{
			System.out.println("childrenHit 오류 : "+ e);
		}
		
		return count;
	}
	
	
	//게시글 삭제
	public int boardDelete(long articleId)
	{
		int count = 0;
		
		try
		{
			count = articleDao.boardDelete(articleId);
		}
		catch(Exception e)
		{
			System.out.println("boardDelete DB오류"+ e);
		}
			
		if(count > 0)
		{
			//파일등 다른 작업 필요시 여기서 함
		}
		return count;
	}
	
	//댓글 삭제
	public long commentDelete(long parantId)
	{
		int count = 0;
		
		try
		{
			count = articleDao.commentDelete(parantId);
		}
		catch(Exception e)
		{
			System.out.println("commentDelete DB오류: "+ e);
		}
		
		return count;
	}
	
	//총게시글 수
	public int getBoardListCnt(long boardId) throws Exception {
		
		int count = 0;
		
		try
		{
			count = articleDao.getBoardListCnt(boardId);
		}
		catch(Exception e)
		{
			System.out.println("getBoardListCnt DB오류 : "+ e);
		}
		return count;
	}

	//페이징 번호 맞게 출력
	public List<Article> getBoardList(PagingA paging, long boardId) throws Exception {

		List<Article> list = articleDao.getBoardList(paging, boardId);
		
		return list;
	}
	
	//게시글 업데이트
	public int boardUpadte(Article article)
	{
		int count = 0;
		
		try
		{
			count = articleDao.boardUpadte(article);
		}
		catch(Exception e)
		{
			System.out.println("게시글 업데이트 오류");
			System.out.println(e);
		}
		return count;
	}
	
	//댓글 리스트
	public List<Article> commentList(long parantId)
	{
		List<Article> list = null;
		
		try
		{
			list = articleDao.commentList(parantId);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return list;
	}
	


	
	
		
}

