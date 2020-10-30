<<<<<<< HEAD
# FinPic

# we are going to make funny webpage!

# swagger API 문서화
서버 실행 후 http://127.0.0.1/swagger-ui.html 접속<br>
controller기능, 내용 확인 및 작업 가능<br>

# pom.xml
<<<<<<< HEAD:FinPic-Vue.dev1.01/README.md
	
=======

>>>>>>> a194a3129057d8692cbbb1ba310fe856ac812fd9:README.md
	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<!-- maven으로 oracle DB driver setting시 repository 설정도 해줘야한다 구글링 ㄱㄱ <dependency> 
			<groupId>com.oracle.database.jdbc</groupId> <artifactId>ojdbc8</artifactId> 
			<scope>runtime</scope> </dependency> -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>

		<!-- jdk 9 이상의 경우 xml처리를 위한 jaxb-api 필요 -->
		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.1</version>
		</dependency>

		<dependency>
			<groupId>com.jslsolucoes</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.2.0.1.0</version>
		</dependency>


		<!-- jsp 사용을 위한 설정 -->
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<version>9.0.36</version>
		</dependency>


		<!-- jstl 라이브러리 -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->

		<!-- swagger 설정 -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.8.0</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.8.0</version>
		</dependency>

		<!-- 아래는 swagger필요 없지만 설정이 잘 안되서 찾은 api 구동 영향은 아마 없음 -->
		<dependency>
			<groupId>com.google.guava</groupId>
			<artifactId>guava</artifactId>
			<version>26.0-jre</version>
<<<<<<< HEAD:FinPic-Vue.dev1.01/README.md
		</dependency>
=======
		</dependency
>>>>>>> a194a3129057d8692cbbb1ba310fe856ac812fd9:README.md
		
		<!-- Base64로 변환하기 위한 api들을 import하는 종속성 -->
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>1.4</version>
		</dependency>
<<<<<<< HEAD:FinPic-Vue.dev1.01/README.md
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
=======
>>>>>>> a194a3129057d8692cbbb1ba310fe856ac812fd9:README.md

# application.properties

server.port=80

#DataSource Setting<br>
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver<br>
spring.datasource.url=jdbc:oracle:thin:@127.0.0.1:1521:xe<br>
spring.datasource.username=SCOTT<br>
spring.datasource.password=TIGER<br><br>


#JPA Setting<br>
spring.jpa.hibernate.ddl-auto=create<br>
#spring.jpa.hibernate.ddl-auto=none<br>
spring.jpa.generate-ddl=false<br>
spring.jpa.show-sql=true<br>
spring.jpa.properties.hibernate.format_sql=true<br>
spring.jpa.database=oracle<br>
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect<br><br>


#Logging Setting<br>
logging.level.org.hibernate=info<br><br>

jwt.expmin=30<br>
jwt.salt=MYSALT<br>

# Vue 설정
  - vue create frontend<br>
    1) Manually select features<br>
    2) Router 추가 설정 나머지 default<br>
    
  
  - axios 설정 내용<br>
    1) npm axios 설치<br>
      npm install axios
  - main.js (내용)<br>
  
import Vue from 'vue'<br>
import App from './App.vue'<br>
import router from './router'<br>
import axios from 'axios'<br><br>

Vue.config.productionTip = false<br>
Vue.prototype.$axios = axios;<br><br>

const storage = window.sessionStorage;<br>
axios.defaults.headers.common["jwt-auth-token"] = storage.getItem("jwt-auth-token");<br>

new Vue({<br>
  router,<br>
  render: h => h(App)<br>
}).$mount('#app')<br><br>

  - Vuex 추가 설정 
    
# Vue components 
1페이지(메인 페이지)<br>
핀픽로고 : 클릭 시, 메인페이지로 이동
검색창 : 검색 결과로 이동
로그인 : 로그인 페이지로 이동
이미지 클릭(무한로딩, 트렌트 순으로 정렬) : 이미지가 게시된 페이지로 이동

2페이지(메인 페이지)<br>
업로드 : 클릭시, 업로드로 이등(로그인 되어 있는 상태에서)
Mypage : 마이 페이지로 이동
로그아웃 : 로그아웃 후, 메인페이지로 이동

3페이지(메인 페이지)<br>
검색명 및 검색 결과 수 : 검색명을 명시하고, 그 검색명에 따른 결과 수를 보여줌

5페이지(로그인 페이지)<br>
로그인 : 아이디, 비밀번호를 받아 로그인, 후에 로그인 된 상태로 메인페이지 이동
가입하기 : 가업하기 페이지로 넘어감

6페이지(가입하기 페이지)<br>
sign up : 이메일, 닉네임, 비밀번호, 비밀번호 검증 총 4개의 값을 받고 사인업 버튼 클릭시 메인페이지 이동, 이메일 || 닉네임 중복되면 sign up 비활성화

7페이지(업로드)<br>
이미지 업로드 : 클릭하면 이미지 가져오기
게시하기 : 태그입력 받고 앞선 업로드된 데이터를 포함해 게시, 게시하기 클릭 후 게시된 게시물로 이동

8페이지(마이페이지)<br>
프로필 이미지, 이름 , 설명 : 기존 입력되어 있는 것 창에 보여줌
favorite : favorite 모아논 것 보여주는 것
MyPage 수정 : 클릭하면 MyPage 수정으로 들어감
이미지 클릭 : 그 이미지를 게시한 게시글로 들어감
follower : 팔로워 수를 보여줌, 클릭 시 자신을 팔로워한 유저 명단 나옴
(확장)검색 : 내가 올린 게시글 중에 검색 가능

9페이지(Favorite)<br>
이미지 클릭 : 그 이미지를 게시한 게시글로 들어감(마이페이지의 이미지 클릭과는 다름)
(확장)검색 : 내가 올린 게시글 중에 검색 가능

10페이지(개인정보 수정)<br>
Save(개인정보 수정) : 닉네임, 이메일, 사용자 설명 값을 받고, save 클릭시 마이페이지로 이동
Save(SNS address) : 각각의 SNS 주소값을 받고, save 클릭시 마이페이지로 이동
계정 삭제 : 클릭시 계정 삭제되고 메인페이지로 이동

11페이지(다른 사람 게시물)<br>
follow : 이 게시글을 올린 유저를 follow함, 그 유저는 follower 증가
like : 좋아요 선택, 게시글은 좋아요 수 늘어남
Favotrite : 클릭시 Favorite 새로 등록
Download : 다운로드하게 함
Copy link : 링크 복사해줌
조회수 : 이 글의 like 수를 보여줌
=======
# signup

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
>>>>>>> 3a49c065ed7ffc869d076ce97da124d2f4f5e657
