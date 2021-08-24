package com.icia.wapoo.controller;


import com.icia.wapoo.S3.S3Service;
import com.icia.wapoo.jwt.service.JwtService;

import com.icia.wapoo.model.Article;
import com.icia.wapoo.model.ImageFile;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icia.wapoo.service.ArticleService;
import com.icia.wapoo.service.MemberService;

import java.io.IOException;
import java.util.ArrayList;
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
    
    @Autowired
	private final S3Service s3Service = null;

	/**
	 * 작성자 : 김건우
	 * @param boardId 1: 공지사항, 2: ... 여기에 값을 넣으면 그 카테고리에있는 게시물을 다 가져옴
	 * @return
	 */
    //본인 확인
    @PostMapping("/articleVerify")
    public ResponseEntity  verify(@RequestBody Map<String, Object> writeForm,HttpServletRequest request)
    {
    	System.out.println("본인 확인");
		
		//System.out.println("글 multipartFile" + multipartFile);
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
		
		Map<String, Object> params = (Map<String, Object>) writeForm.get("params");
		System.out.println(params);
		
		Article article = null;
		
		long articleId = (((Integer) params.get("articleId")).intValue());
		article = articleService.boardList(articleId);
		System.out.println(article);
		System.out.println("memberId : " + memberId);
		
		if(article != null)
		{
			if(article.getStatus().equals("Y"))
			{
				System.out.println("글 확인 가능");
				return new ResponseEntity("100", HttpStatus.OK);
			}
			else
			{
				if(article.getWriterId() == memberId)
				{
					System.out.println("비공개 글 확인 가능");
					return new ResponseEntity("100", HttpStatus.OK);
				}
				else
				{
					System.out.println("글 확인 불가");
					return new ResponseEntity("250", HttpStatus.OK);
				}
			}
		}
		else
		{
			System.out.println("글 없음");
			return new ResponseEntity("400", HttpStatus.OK);
		}
		
    	
    	
    }
    
    
  //이미지 업로드
    @PostMapping("/imageUpload")
    public List<ImageFile> imageFile(@RequestPart(value="image", required=false) List<MultipartFile> image)
    {
    	System.out.println("이미지 업로드");
    	System.out.println(image);
    	
    	List<ImageFile> files = new ArrayList<>();
         
    	 for(MultipartFile file: image)
    	 {
    		 ImageFile articleImageFile = new ImageFile();
        	 String fileURL = null;
    		 
    		 try 
             {
                fileURL = s3Service.upload(file, "image_article_");
             } 
             catch (IOException e) 
             {
                 throw new RuntimeException("S3 업로드중 오류발생!");
             }
    		 
    		 articleImageFile.setFilesize(file.getSize());				//파일 사이즈
             articleImageFile.setFiletype(file.getContentType());		//파일 타입
             articleImageFile.setName(fileURL);							//파일 경로
             articleImageFile.setOrgName(file.getName());				//원래 이름
             
             files.add(articleImageFile);
             
             
             
    	 }
    		 
    	 System.out.println(files);
    	 
    	 
         
         
    	
    	
    	return files;
    }
	
	//테스트
	@GetMapping(value="/test")
    public String testA(@RequestParam Map<String, Object> userData)
    {
        System.out.println();
    	return "테스트요청";
    }
	
	
	//글 업로드  @RequestPart(value="file", required=true)
	@PostMapping("/board/writeProc")
	public ResponseEntity write( @RequestBody Map<String, Object> writeForm, HttpServletRequest request)
	{
		System.out.println("글 업로드");
		
		//System.out.println("글 multipartFile" + multipartFile);
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
		
		if(memberId > 0)
		{
			System.out.println("회원이다.");
			
			Map<String, Object> writeData = (Map<String, Object>) writeForm.get("params");
			System.out.println("writeData : " + writeData);
			
			int count = 0;
			
			Article article = new Article();
			
			
			article.setTitle((String)writeData.get("title"));

			article.setBody((String)writeData.get("body"));
			
			article.setStatus((String)writeData.get("status"));
			
			article.setBoardId( Integer.parseInt((String)writeData.get("boardId")));
			
			article.setWriterId(memberId);
			
			count = articleService.listInsert(article, memberId);
			
			if(count > 0)
			{
				System.out.println("글 업로드");
				
				
					//이미지 체크
					if(writeData.size() -4 > 0)
					{
						System.out.println("이미지 업로드");
						
						for(int i = 0; i < writeData.size() -4; i++)
						{
							Map<String, String> map = (Map<String, String>) writeData.get("image" + i);
					
							ImageFile image = new ImageFile();
							
							image.setOrgName(map.get("orgName"));
							
							image.setName(map.get("name"));
							
							image.setFiletype(map.get("filetype"));
							
							String filesize = String.valueOf(map.get("filesize"));
							
							int score = Integer.parseInt(String.valueOf(map.get("filesize")));

							image.setFilesize(score );
							
							image.setRef_id((int)article.getArticleId());
							
							
							articleService.registerStore(image);
							
						}
						
						
						System.out.println("글작성, 이미지 성공 \n");
						return new ResponseEntity("100", HttpStatus.OK);
						
					}
					else
					{
						System.out.println("글작성, 이미지 없음 \n");
						return new ResponseEntity("100", HttpStatus.OK);
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

	private long parseInt(String string) {
		// TODO Auto-generated method stub
		return 0;
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
		//파일
		List<ImageFile> articleImageFile = null;
		
		if(articleId > 0)
		{
			article = articleService.boardList(articleId);//게시글
			list = articleService.commentList(article.getArticleId());//댓글
			System.out.println("list[0] : " + list);
			//파일
			
			articleImageFile = articleService.imageFileList(articleId);
			
			if(articleImageFile != null)
			{
				System.out.println("이미지 있음");
			}
			else
			{
				System.out.println("이미지 없음");
			}

			if(articleService.boardHit(articleId) > 0)//조회수 증가
			{
				System.out.println("조회수 증가");
			}
			
			if(article != null && memberId != article.getWriterId())//글 존재 여부, 작성자여부 확인
			{	
				System.out.println("작성자 아님");
			}
			else
			{
				MYPAGE = true;//작성자여부 true
				
				System.out.println("조회수 뻥튀기 안됌");
			}	
		}
		
		map.put("MYPAGE", MYPAGE);
		map.put("article", article);
		map.put("list", list);
		map.put("articleImageFile", articleImageFile);
		
		
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
		
		
		if(memberId != 0)
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


    
    
    
    
    

