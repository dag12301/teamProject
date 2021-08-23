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

	//게시글 등록

	public int boardInsert(Article article);
	//댓글 등록
	public int commentInsert(Article article);
	//게시물 댓글수 증가
	public int childrenHit(long articleId);
	// 게시글 조회
	Article boardSelect(long articleId);
	//게시글 삭제

	public int boardDelete(long articleId);
	// 댓글 삭제
	public int commentDelete(long parantId);

	//게시글 view
	Article boardList(@Param("articleId") long articleId);
	//조회수 증가
	int boardHit(@Param("articleId") long articleId);
	
	//총 게시글 개수 확인
	int getBoardListCnt(long boardId) throws Exception;
	// 페이징 개수
	public List<Article> getBoardList(PagingA paging, long boardId) throws Exception;
	//게시글 수정
	public int boardUpadte(Article article);
	//댓글 조회
	public List<Article> commentList(long parantId);

	


	
}
