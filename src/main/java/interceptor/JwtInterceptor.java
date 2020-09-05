package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
import service.JwtService;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtService jwtService;

	// 로그인 후 정보 확인 버튼 클릭시에만 호출되는 메소드
	// 로그인 후엔 jwt-auth-token이 존재 따라서 검증, 없을 경우 예외 발생
	/**
	 * preHandle() : controller로 보내기 전에 처리 Object - 핸들러 정보 jwt-auth-token 없을 경우 생성,
	 * 존재할 경우 유효성 검증
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		System.out.println("요청 url " + req.getMethod() + " : " + req.getServletPath());
		// request의 parameter에서 auth_token으로 넘어온 녀석을 검색
		System.out.println("---- " + req.getHeader("jwt-auth-token"));
		System.out.println("---- " + req.getMethod());
  
		String token = req.getHeader("jwt-auth-token"); 


		if (token != null && token.length() > 0) {
			// 유효한 토큰이면 진행, 아니면 예외 발생
			log.info("토큰 사용 가능 : {} ", token);
			jwtService.checkValid(token);
			return true; // 컨트롤러 요청
		} else {
			System.out.println("인증 토큰이 없을때----");
			throw new RuntimeException("인증 토큰이 없음");
		}

	}

}
