package com.icia.wapoo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Article;

import java.util.List;

@Repository
@Mapper
public interface ArticleDao {

	List<Article> selectAllByBoardId(@Param("boardId") int boardId);
	//게시글 등록
	int boardInsert(Article article);
	// 게시글 조회
	Article boardSelect(long articleId);
	//게시글 삭제
	int boardDelete(long articleId);
	
}
