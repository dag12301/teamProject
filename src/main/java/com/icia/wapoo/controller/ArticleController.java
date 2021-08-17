package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Article;
import com.icia.wapoo.model.FileData;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.icia.wapoo.service.ArticleService;
import com.icia.wapoo.service.MemberService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	 @GetMapping(value="/test")
	    public String testA(@RequestParam Map<String, Object> userData)
	    {
	        System.out.println();
	    	return "테스트요청";
	    }
	
	//글 업로드
	@PostMapping("/board/writeProc")
	public ResponseEntity write( @RequestBody Map<String, Object> writeForm)
	{
//		//JWT 토큰값
//		String JWT = jwtService.resolveToken(request);
//		Map<String, Object> token = jwtService.getUserInfo(JWT);
//		// token에서 memberId 값 가져오기
//		int writerId = (int) token.get("memberId");
		//test 멤버 값
		long memberId = 1;
		
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
	public List<Article> boardList(long articleId)
	{
		System.out.println(articleId);
		
		if(articleId > 0)
		{
			
			return articleService.boardList(articleId);
		}
		else
		{
			
			return articleService.boardList(articleId);
		}
		
		
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
		
	
		long writerId = Integer.parseInt((String)ListData.get("writerId"));
		long articleId = Integer.parseInt((String)ListData.get("articleId"));
		
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
	
	
	
	
}


    
    
    
    
    

