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
}
