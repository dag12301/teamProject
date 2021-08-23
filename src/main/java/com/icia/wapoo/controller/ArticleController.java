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
		System.out.println("글 업로드");
		
		long memberId = 0;
		try
		{
			//JWT 토큰값
			String JWT = jwtService.resolveToken(request);
			System.out.println(JWT);
			
			Map<String, Object> token = jwtService.getUserInfo(JWT);
			// token에서 memberId 값 가져오기
			 memberId = (long)((Integer) token.get("memberId")).intValue();
		}
		catch(Exception e)
		{
			System.out.println("memberId 없음");
		}
		//test 멤버 값 
		//long memberId = 1;
		System.out.println("memberId : " + memberId);
		
		if(memberId > 0)
		{
			System.out.println("회원이다.");
			
			Map<String, Object> writeData = (Map<String, Object>) writeForm.get("params");
			System.out.println(writeData);
			
			int count = 0;
			
			
			count = articleService.listInsert(writeData, memberId);
			
			if(count > 0)
			{
				System.out.println("글작성 성공 \n");
				return new ResponseEntity("100", HttpStatus.OK);
			}
			else
			{
				System.out.println("글작성 실패 \n");
				return new ResponseEntity("500", HttpStatus.OK);
			}
		}
		else
		{
			System.out.println("비회원 실패 \n");
			return new ResponseEntity("200", HttpStatus.OK);
		}

	}

	//댓글 업로드
	@PostMapping("/board/commentProc")
	public ResponseEntity commentWrite(@RequestBody Map<String, Object> writeForm,HttpServletRequest request)
	{
		System.out.println("댓글 업로드");
		
		long memberId = 0;
		try
		{
			//JWT 토큰값
			String JWT = jwtService.resolveToken(request);
			System.out.println(JWT);
			
			Map<String, Object> token = jwtService.getUserInfo(JWT);
			// token에서 memberId 값 가져오기
			 memberId = (long)((Integer) token.get("memberId")).intValue();
		}
		catch(Exception e)
		{
			System.out.println("memberId 없음");
		}
		System.out.println("memberId : " + memberId);
		
		if(memberId > 0)//회원 확인
		{
			System.out.println("회원이다.");
			
			Map<String, Object> params = (Map<String, Object>) writeForm.get("params");
			System.out.println(params);
			
			long articleId = (long)(((Integer) params.get("parantId")).intValue());
			
			if(articleService.commentInsert(params, memberId) > 0)
			{
				//childrenHit
				if(articleService.childrenHit(articleId) > 0)
				{
					System.out.println("글작성 성공 \n");
					return new ResponseEntity("100", HttpStatus.OK);
				}
				else
				{
					System.out.println("DB 오류 \n");
					return new ResponseEntity("300: ", HttpStatus.OK);
				}
			}
			else
			{
				System.out.println("글작성 실패 \n");
				return new ResponseEntity("500", HttpStatus.OK);
			}
		}
		else
		{
			System.out.println("비회원 실패 \n");
			return new ResponseEntity("200", HttpStatus.OK);
		}
		
		
	}
	
	//게시글 죄회  
	@GetMapping("/board/list")
	public Map<String, Object> boardList(long articleId, HttpServletRequest request)
	{
		System.out.println("게시글 조회");
		//조회 값 없으면
		long memberId = 0;
		
		try
		{
			//JWT 토큰값
			String JWT = jwtService.resolveToken(request);
			System.out.println(JWT);
			
			Map<String, Object> token = jwtService.getUserInfo(JWT);
			// token에서 memberId 값 가져오기
			 memberId = (long)((Integer) token.get("memberId")).intValue();
		}
		catch(Exception e)
		{
			System.out.println("memberId 없음");
		}
		
		System.out.println("memberId : " + memberId);
		
		
		Map<String, Object> map = new HashMap<>();//여기 담아서 보냄
		boolean MYPAGE = false; 				  //작성자 여부
		List<Article> list = null;				  //댓글 여기에 보냄		
		Article article = null;
		
		if(articleId > 0)
		{
			article = articleService.boardList(articleId);//게시글
			list = articleService.commentList(article.getArticleId());//댓글
			System.out.println("list[0] : " + list);
			
			if(article != null && memberId != article.getWriterId())//글 존재 여부, 작성자여부 확인
			{	
				if(articleService.boardHit(articleId) > 0)//조회수 증가
				{
					System.out.println("조회수 증가");
				}
			}
			else
			{
				MYPAGE = true;//작성자여부 true
				
				System.out.println("게시를 없거나, 조회수 뻥튀기 안됌");
			}	
		}
		
		map.put("MYPAGE", MYPAGE);
		map.put("article", article);
		map.put("list", list);
		
		
		
		return map;
	}
	
	
	//페이지 삭제
	@PostMapping("/board/delete")
	public ResponseEntity boardDelete(@RequestBody Map<String, Object> ListData, HttpServletRequest request)
	{
		System.out.println("페이지 삭제");
		
		//조큰 값 없으면
		long memberId = 0;

		try
		{
			//JWT 토큰값
			String JWT = jwtService.resolveToken(request);
			System.out.println(JWT);
			
			Map<String, Object> token = jwtService.getUserInfo(JWT);
			// token에서 memberId 값 가져오기
			 memberId = (long)((Integer) token.get("memberId")).intValue();
			 System.out.println("memberId 있음");
		}
		catch(Exception e)
		{
			System.out.println("memberId 없음");
		}
		//테스트 
		//long memberId = 1;
		
		Map<String, Object> params = (Map<String, Object>) ListData.get("params");
		System.out.println(params);
		
		long writerId = (long)(((Integer) params.get("writerId")).intValue());
		
		long articleId = (long)(((Integer) params.get("articleId")).intValue());
		
		System.out.println("writerId : " +writerId);
		System.out.println("articleId : " +articleId);
		System.out.println("memberId : " + memberId);
		
		
		if(memberId == 0)
		{
			if(writerId == memberId)
			{
				Article article = null;
				
				article = articleService.boardList(articleId);
				
				if(article != null)//글 존재 여부
				{
					System.out.println("articleId : " +articleId +" 글 있다.");
					
					if(article.getChildren() > 0)//댓글여부 확인
					{
						long parantId = articleId;
						System.out.println("본글이다 :" + article.getBoardId() +"댓글 삭제 필요");
						if(articleService.commentDelete(parantId) > 0)//댓글 삭제 확인
						{
							System.out.println("댓글 삭제 성공 \n");
							
							if(articleService.boardDelete(articleId) > 0)
							{
								System.out.println("댓글 삭제, 글삭제: 성공 \n");
								return new ResponseEntity("100", HttpStatus.OK);
							}
							else
							{
								System.out.println("댓글 삭제 성공, 글삭제: DB 오류 \n");
								return new ResponseEntity("300", HttpStatus.OK);
							}
						}
						else
						{
							System.out.println("댓글 삭제 DB 오류 \n");
							return new ResponseEntity("300", HttpStatus.OK);
						}
					}
					else
					{
						System.out.println("댓글 없음 글삭제");
						if(articleService.boardDelete(articleId) > 0)
						{
							System.out.println("글삭제: 성공 \n");
							return new ResponseEntity("100", HttpStatus.OK);
						}
						else
						{
							System.out.println("글삭제: DB 오류 \n");
							return new ResponseEntity("300", HttpStatus.OK);
						}
					}
				}
				else
				{
					System.out.println("글삭제 없음 \n");
					return new ResponseEntity("400", HttpStatus.OK);
				}
			}
			else
			{
				System.out.println("작성자 아님 \n");
				return new ResponseEntity("250", HttpStatus.OK);
			}
		}
		else
		{
			System.out.println("비회원 \n");
			return new ResponseEntity("200", HttpStatus.OK);
		}
		
	}
	
	
	//게시글 수정
	@PostMapping("/board/update")
	public ResponseEntity boardUpdate(@RequestBody Map<String, Object> ListData, HttpServletRequest request)
	{
		System.out.println("개시글 수정");
		long memberId = 0;

		try
		{
			//JWT 토큰값
			String JWT = jwtService.resolveToken(request);
			System.out.println(JWT);
			
			Map<String, Object> token = jwtService.getUserInfo(JWT);
			// token에서 memberId 값 가져오기
			 memberId = (long)((Integer) token.get("memberId")).intValue();
			 System.out.println("memberId 있음");
			//memberId = 1;
		}
		catch(Exception e)
		{
			System.out.println("memberId 없음");
		}
		
		
		//업데이트 글 정보
		Map<String, Object> params = (Map<String, Object>) ListData.get("params");
		System.out.println(params);
	
		String title = (String)params.get("title");
		String body = (String)params.get("body");
		
		
		long articleId = (long) ((Integer) params.get("articleId")).intValue();
		
		System.out.println("articleId : " +articleId);
		
		Article article = null;
		
		if(memberId == 0)
		{
			if(articleId > 0)
			{
				article = articleService.boardList(articleId);
				
				if(article != null)
				{
					if(memberId == article.getWriterId())
					{
						article.setTitle(title);
						article.setBody(body);
						
						if(articleService.boardUpadte(article) > 0)
						{
							System.out.println("성공");
							return new ResponseEntity("100", HttpStatus.OK);
						}
						else
						{
							System.out.println("시스템 오류");
							return new ResponseEntity("500", HttpStatus.OK);
						}
					}
					else
					{
						System.out.println("작성자 아님");
						return new ResponseEntity("250", HttpStatus.OK);
					}
				}
				else
				{
					System.out.println("DB오류");
					return new ResponseEntity("300", HttpStatus.OK);
				}
			}
			else
			{
				System.out.println("글없음");
				return new ResponseEntity("400", HttpStatus.OK);
			}
		}
		else
		{
			System.out.println("비회원");
			return new ResponseEntity("200", HttpStatus.OK);
		}
	}
	
	
	//페이징처리 리스트 
	@GetMapping(value="/pagingBoard")
	public Map<String, Object> getBoardList(@RequestParam(required = false, defaultValue = "1") int page,  //현재 페이지
										    @RequestParam(required = false, defaultValue = "1") int range,		//버튼 prev,next클릭시 페이지 위치
										    @RequestParam(required = false, defaultValue = "10") int listSize,		//리스트 수
										    @RequestParam(required = false, defaultValue = "5") int rangeSize,	//페이지 수
										    @RequestParam long boardId) throws Exception
	{	
		System.out.println("페이징 처리중");
		
		Map<String, Object> map = new HashMap<>();
		
		//전체 게시글 개수
		int total = articleService.getBoardListCnt(boardId);

	    //Pagination 객체생성
		PagingA paging = new PagingA();

		paging.pageInfo(page, range,listSize,rangeSize, total);
		//페이지 리스트 수가 rangeSize보다 작을 때
		if(range == 1 && paging.getTotalpage() <= paging.getEndPage())
		{
			System.out.println("작아 \n\n ");
			paging.setEndPage(paging.getTotalpage());
			
			range = 1;
			
			paging.setNext(false);
			paging.setPrev(false);
		}
		
		System.out.println("total : " + total);
		System.out.println("page : " + page );
		System.out.println("StartList : " + paging.getStartList());
		System.out.println("range : " + range );
		System.out.println("pageCnt : " + paging.getTotalpage());
		System.out.println(paging.getStartList());
		System.out.println("startPage: " + paging.getStartPage());
		System.out.println("endPage: " + paging.getEndPage());
		
		List<Article>  list =  articleService.getBoardList(paging, boardId);
		
		map.put("paging", paging);
		map.put("list", list);
		
		return map;
	}
	
	
	
	
}


    
    
    
    
    

