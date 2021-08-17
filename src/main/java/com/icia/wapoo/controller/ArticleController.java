package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Article;

import com.icia.wapoo.service.ArticleService;
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

import com.icia.wapoo.service.MemberService;

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
	public List<Article> listAll(int boardId) {
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
	public ResponseEntity write(HttpServletRequest request, @RequestBody Map<String, Object> loginData)
	{
//		//JWT 토큰값
//		String JWT = jwtService.resolveToken(request);
//		Map<String, Object> token = jwtService.getUserInfo(JWT);
//		// token에서 memberId 값 가져오기
//		int writerId = (int) token.get("memberId");
		
		String title = request.getParameter("title");	//글 제목
		String status = (String) loginData.get("status");	//게시물 상태(공개,비공개)
		String body = request.getParameter("body");		//게시글 내용
		
		int star = Integer.parseInt((String)loginData.get("status"));
		
		System.out.println(loginData.get("title"));
		System.out.println(body);
		System.out.println(status);
		System.out.println();
		System.out.println(star);
	
		if(title != null && body != null)
		{
			Article article = new Article();
			
			//article.setWriterId(writerId);  //유저 id
			article.setTitle(title);
			article.setBody(body);
			//article.setStatus(status);
			article.setBoard_id(1);
			
			int count = 0;
			count = articleService.listInsert(article);
			
			if(count > 0)
			{
				return new ResponseEntity("성공", HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity("DB오류", HttpStatus.OK);
			}
			
		}
		else
		{
			return new ResponseEntity("제목, 내용 없음", HttpStatus.OK);
		}

	}
	
	
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
			System.out.println("1111111111111111111111111111111");
			if(articleService.boardDelete(articleId) > 0)
			{
				System.out.println("22222222222222222222222222");
				return new ResponseEntity("성공1", HttpStatus.OK);
			}
		}
		else
		{
			System.out.println("3333333333333333333333333333");
			return new ResponseEntity("멤버, 글쓴 아이디 다름", HttpStatus.OK);
		}
		
		System.out.println("444444444444444444444444");
		return new ResponseEntity("실패", HttpStatus.OK);
	}
	
	
	
	
}


    
    
    
    
    

