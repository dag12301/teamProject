package com.icia.wapoo.controller;


import com.icia.wapoo.jwt.service.JwtService;

import com.icia.wapoo.model.Article;
import com.icia.wapoo.paging.PagingA;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icia.wapoo.service.ArticleService;
import com.icia.wapoo.service.MemberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

	@Autowired
    private ArticleService articleService;
	
	//JWT 값 받기
	@Autowired
    private JwtService jwtService;
    @Autowired
    private MemberService memberService;

	/**
	 * 작성자 : 김건우
	 * @param boardId 1: 공지사항, 2: ... 여기에 값을 넣으면 그 카테고리에있는 게시물을 다 가져옴
	 * @return
	 */
	@GetMapping("/board")
	public List<Article> listAll(long boardId) {
		return articleService.listAllByBoard(boardId);
		
		
		
		
	}
	
	//테스트
	@GetMapping(value="/test")
    public String testA(@RequestParam Map<String, Object> userData)
    {
        System.out.println();
    	return "테스트요청";
    }
	
	//글 업로드
	@PostMapping("/board/writeProc")
	public ResponseEntity write( @RequestBody Map<String, Object> writeForm,HttpServletRequest request)
	{
		//JWT 토큰값
		String JWT = jwtService.resolveToken(request);
		System.out.println(JWT);
		System.out.println("1---------------------------");
		Map<String, Object> token = jwtService.getUserInfo(JWT);
		
		System.out.println("2---------------------------");
		System.out.println(token);
		// token에서 memberId 값 가져오기
		System.out.println( token.get("memberId"));
		System.out.println("4---------------------------");
		System.out.println(token.get("memberId").getClass());
		System.out.println("3---------------------------");
		
		
		System.out.println( ((Integer) token.get("memberId")).intValue() );

		
//		
//		
		long memberId = (long)((Integer) token.get("memberId")).intValue();

		//test 멤버 값 
		//long memberId = 1;
		System.out.println(memberId);
		
		Map<String, Object> writeData = (Map<String, Object>) writeForm.get("params");
		System.out.println(writeData);
		
		int count = 0;
		
		
		count = articleService.listInsert(writeData, memberId);
		
		if(count > 0)
		{
		
			return new ResponseEntity("성공", HttpStatus.OK);
		}
		else
		{
			
			return new ResponseEntity("DB오류", HttpStatus.OK);
		}
			

	}
	
	//게시글 죄회
	@GetMapping("/board/list")
	public Article boardList(long articleId, HttpServletRequest request)
	{
		//조큰 값 없으면
		long memberId = 0;
		
		try
		{
			//JWT 토큰값
			String JWT = jwtService.resolveToken(request);
			Map<String, Object> token = jwtService.getUserInfo(JWT);
			// token에서 memberId 값 가져오기
			 memberId = (long) token.get("memberId");
		}
		catch(Exception e)
		{
			System.out.println("memberId 없음");
		}
		System.out.println(articleId);
		
		
		//articleId
		System.out.println(memberId);
		
		Article article = null;
		
		if(articleId > 0)
		{
			
			article = articleService.boardList(articleId);
			
			if(article != null && memberId != article.getWriterId())
			{
				//조회수 증가
				int count = 0;
				
				count = articleService.boardHit(articleId);
				
				
				if(count > 0)
				{
					System.out.println("조회수 증가");
				}
			}
			else
			{
				System.out.println("게시를 없거나, 조회수 뻥튀기 안됌");
			}
			
			
		}
		
		
		return article;
	}
	
	
	//페이지 삭제
	@PostMapping("/board/delete")
	public ResponseEntity boardDelete(@RequestBody Map<String, Object> ListData, HttpServletRequest request)
	{
		//JWT 토큰값
//		String JWT = jwtService.resolveToken(request);
//		Map<String, Object> token = jwtService.getUserInfo(JWT);
//		// token에서 memberId 값 가져오기
//		long memberId = (int) token.get("memberId");
		//테스트 
		long memberId = 1;
		
		Map<String, Object> params = (Map<String, Object>) ListData.get("params");
		System.out.println(params);
		String i = (String)params.get("writerId");
		
		
	
		long writerId = (long)Integer.parseInt((String)params.get("writerId"));
		long articleId = (long) Integer.parseInt((String)params.get("articleId"));
		
		System.out.println("writerId : " +writerId);
		System.out.println("articleId : " +articleId);
		
		if(writerId == memberId)
		{
			
			if(articleService.boardDelete(articleId) > 0)
			{
				
				return new ResponseEntity("성공1", HttpStatus.OK);
			}
		}
		else
		{
			
			return new ResponseEntity("멤버, 글쓴 아이디 다름", HttpStatus.OK);
		}
		
	
		return new ResponseEntity("실패", HttpStatus.OK);
	}
	
	
	//페이징처리 리스트
	@GetMapping(value="/pagingBoard")
	public Map<String, Object> getBoardList(@RequestParam(required = false, defaultValue = "1") int page,
									  @RequestParam(required = false, defaultValue = "1") int range,
									  @RequestParam long boardId) throws Exception 
	{
		System.out.println("page : " +page);
		System.out.println("page : " +page);
		System.out.println("boardId : " +boardId);
		
		//Map<String, Object> writeData = (Map<String, Object>) writeForm.get("params");
		Map<String, Object> map = new HashMap<>();
		
		//전체 게시글 개수
		int total = articleService.getBoardListCnt(boardId);

	    //Pagination 객체생성
	
			PagingA paging = new PagingA();
	
			paging.pageInfo(page, range, total);
			
			System.out.println(paging.getStartList());
			
			System.out.println("page : " + page);
			System.out.println("range: " + range);
			System.out.println("endList: " + paging.getEndList());
			
			System.out.println(paging.getEndPage());
			System.out.println(paging.getStartPage());
	
			List<Article>  list =  articleService.getBoardList(paging, boardId);
			
			map.put("paging", paging);
			map.put("list", list);
			
	
			return map;
	}
	
	//페이징 필요값들
	@GetMapping(value="/pagingBoard/paging")
	public PagingA paging(@RequestParam(required = false, defaultValue = "1") int page,
			  @RequestParam(required = false, defaultValue = "1") int range,
			  @RequestParam long boardId) throws Exception 
	{
		//전체 게시글 개수
		int listCnt = articleService.getBoardListCnt(boardId);
				
		PagingA paging = new PagingA();
		
		paging.pageInfo(page, range, listCnt);
		
		return paging;
	}
	
	
}


    
    
    
    
    

