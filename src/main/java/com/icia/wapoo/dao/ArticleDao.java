package com.icia.wapoo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Article;

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
	//게시글 조회표현
	List<Article> boardList(@Param("articleId") long articleId);
	
}
