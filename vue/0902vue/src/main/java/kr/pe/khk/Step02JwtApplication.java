package kr.pe.khk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.pe.khk.interceptor.JwtInterceptor;


/* WebMvcConfigurer
 * Interceptor를 등록하기 위해서 사용되는 API
 * 등록 -> 적용할 경로, 제외할 경로 지정
 */
@SpringBootApplication
public class Step02JwtApplication implements WebMvcConfigurer {

	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("addInterceptors() --");
		//기본 적용 경로로, 적용 제외 경로
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**").excludePathPatterns(Arrays.asList("/api/user/**"));
		
	}
	
	//CORS(Cross-Origin Resource Sharing)
	//웹 브라우저에서 외부 도메인 서버와 통신하기 위한 방식을 표준화한 스펙
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("addCorsMappings() --");
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.allowedHeaders("jwt-auth-token");
	}

	public static void main(String[] args) {
		SpringApplication.run(Step02JwtApplication.class, args);
	}
} 
