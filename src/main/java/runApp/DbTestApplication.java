package runApp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.JwtInterceptor;


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("repositories")
@EntityScan(basePackages = {"usersDTO","tagsDTO","profilesDTO","picturesDTO","pictures.and.tagsDTO","followersDTO","favorite.picturesDTO","pictures.and.usersDTO"})
@ComponentScan(basePackages = {"scheduler","interceptor","service","controller","repositories","usersDTO","tagsDTO","profilesDTO","picturesDTO","pictures.and.tagsDTO","followersDTO","favorite.picturesDTO","pictures.and.usersDTO"})
public class DbTestApplication implements WebMvcConfigurer{
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("addInterceptors() --");
		//기본 적용 경로로, 적용 제외 경로
		//Storage에 index.html 없이 저장하는법?
		registry.addInterceptor(jwtInterceptor).addPathPatterns("").excludePathPatterns(Arrays.asList(""));
		
	}
	
	//CORS(Cross-Origin Resource Sharing)
	//웹 브라우저에서 외부 도메인 서버와 통신하기 위한 방식을 표준화한 스펙
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("addCorsMappings() --");
		registry.addMapping("/**")
		.allowedOrigins("*")
		.exposedHeaders("jwt-auth-token");
	}
	public static void main(String[] args) {
		SpringApplication.run(DbTestApplication.class, args);
	}

}
