package config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket swaggerApi() {
        
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(200)
                .message("신호ok")	
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(404)
                .message("찾을 수 없음")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("서버 error")
                .build());

    	
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("controller"))
                .paths(PathSelectors.any()) //"a/v/**" 같은 설정 가능
                .build()
                .globalResponseMessage(RequestMethod.GET, responseMessages);
             //   .useDefaultResponseMessages(false);  // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
    }
    
    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Hello API Documentation")
                .description("FinPic 코드 API 문서")
                .license("구글로 이동").licenseUrl("http://www.google.com").version("2").build();
    }
}
