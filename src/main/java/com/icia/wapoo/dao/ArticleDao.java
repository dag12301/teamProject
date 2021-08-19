package com.icia.wapoo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Article;
import com.icia.wapoo.paging.PagingA;

import java.util.List;

@Repository
@Mapper
public interface ArticleDao {

	List<Article> selectAllByBoardId(@Param("boardId") long boardId);
	//게시글 등록
	public int boardInsert(Article article);
	// 게시글 조회
	public Article boardSelect(long articleId);
	//게시글 삭제
	public long boardDelete(long articleId);
	//게시글 view
	public Article boardList(@Param("articleId") long articleId);
	//조회수 증가
	public int boardHit(@Param("articleId") long articleId);
	
	//총 게시글 개수 확인
	public int getBoardListCnt(long boardId) throws Exception;
	// 페이징 개수
	public List<Article> getBoardList(PagingA paging, long boardId) throws Exception;

	


	
}
