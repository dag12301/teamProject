package com.icia.wapoo.jwt.service;

import com.icia.wapoo.model.Member;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import java.util.Base64;
import java.util.Date;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service("jwtService")
public class JwtService {

//    @Value("${jwt.salt}")
//    private static String SALT;
    private String secretKey = "testxxxtesttesttesttesttest";

//    @Value("${jwt.expmin}")
//    private Long expMin;
    private static final long expMin = 60;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }




    /**
     * 토큰 생성
     * @param member
     * @return
     */
    public String create(final Member member){
        log.trace("time: {}", expMin);

        System.out.println("JwtService.create : 토큰 생성을 시작합니다.");
        Date NOW = new Date();
        Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(NOW)
                .setExpiration(new Date(NOW.getTime() + (1000*60*10)));
        //값을 세팅한다.
        claims.put("memberId", member.getMemberId());
        claims.put("nickname", member.getNickname());
        claims.put("role", member.getRole());

        final String jwt = Jwts.builder()
                        .setHeaderParam("typ", "JWT")
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();

        System.out.println("JwtService.create : 토큰 생성이 완료되었습니다.");
        log.debug("토큰 발행 : {}", jwt);
        return jwt;
    }


    // Request의 Header 에서 값 추출.
    public String resolveToken(HttpServletRequest request) {
        System.out.println("JwtService.resolveToken : 토큰을 추출합니다.");
        return request.getHeader("accesstoken");
    }
    
    // 토큰에서 정보 해독
    public Map<String, Object> getUserInfo(String token) {
        System.out.println("JwtService.getUserInfo : 토큰에서 정보를 추출합니다.");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


    // 토큰 유효 + 만료일자
    public boolean validateToken(String jwtToken) {
        try {
            System.out.println("JwtService.validateToken : 유효한 토큰인지 확인합니다.");
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
