package com.icia.wapoo.jwt.service;

import com.icia.wapoo.model.Member;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service("jwtService")
public class JwtService {

//    @Value("${jwt.salt}")
//    private static String SALT;
    private static final String SALT = "testxxx";

//    @Value("${jwt.expmin}")
//    private Long expMin;
    private static final long expMin = 10;

    /**
     * 토큰 생성
     * @param member
     * @return
     */
    public String create(final Member member){
        log.trace("time: {}", expMin);
        final JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ", "JWT")
                //.setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject("LoginToken") // 서브젝트 수정*
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*expMin)))
                .claim("id", member.getId())
                .claim("name", member.getName())
                .claim("email", member.getEmail())
                .signWith(SignatureAlgorithm.HS512, this.generateKey());
        final String jwt = builder.compact();
        log.debug("토큰 발행 : {}", jwt);
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = SALT.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e ) {
            if(log.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                log.error("JWT 키를 만드는 도중 에러발생 {}", e.getMessage());
            }
        }
        return key;
    }

    /**
     * 토큰 검증
     * @param jwt
     */
    public boolean checkValid(final String jwt) {
        log.trace("토큰 점검 : {}", jwt);
        try {
            Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("유효하지 않은 토큰");
        }

    }

    /**
     * 토큰을 분석해서 필요한 정보 반환
     * @param jwt
    * @return
     */
    public Map<String, Object> get (final String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }
        log.trace("claims: {}", claims);
        // claims 는 Map 이다
        return claims.getBody();
    }
}
