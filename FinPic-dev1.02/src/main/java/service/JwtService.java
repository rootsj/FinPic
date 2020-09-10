package service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import usersDTO.Users;

@Component
@Slf4j 
public class JwtService {
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	
	/**
	 * 로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성해서 반환
	 * @param user
	 * @return
	 */
	public String create(final Users user) {
		
		System.out.println("JwtService의 create()----");
		log.trace("time : {} ", expireMin);
		
		final JwtBuilder builder = Jwts.builder();
		
		builder.setHeaderParam("typ", "JWT");
		
		//토큰 제목설정, 유효기간, 담고 싶은 정보
		builder.setSubject("LogIn Token").setExpiration(new Date(System.currentTimeMillis() + 100*60*expireMin)).
		claim("User", user).claim("second", "new data");
		
		//signature - secret key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		//마지막 직렬화 처리
		final String jwt = builder.compact();
		log.debug("토큰 발행 : {} ", jwt);
		return jwt;
	}
	
	/**
	 * 전달받은 토큰이 제대로 생성된 것인지 여부 확인
	 * 문제 발생시 RuntimeException 발생
	 * @param jwt
	 */
	public void checkValid(String jwt) {
		log.info("토큰 검증 : {} ", jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	/**
	 * jwt 토큰을 분석 후에 필요한 정보 반환
	 * @param jwt
	 * @return
	 */
	public Map<String, Object> get(String jwt){
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
			
		}catch(final Exception e) {
			throw new RuntimeException();
		}
		log.trace("claims : {} ", claims);
		
		//Claims은 Map의 구현체
		return claims.getBody();
	}
}
