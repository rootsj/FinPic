# FinPic

### Notice
- devBH1.03 and VueWithCSS are most recent working tree
- If you want to take a look at source of this project, you should change branch to devBH1.03 and VueWithCSS
	- https://github.com/byungs2/Finpic_vue/tree/VueWithCSS
	- https://github.com/byungs2/FinPic/tree/devBH1.03
# Team Members
- 이병헌 (Project Manager)
	- git : https://github.com/byungs2
- 이종화 (Github Director, Follow Function Manager)
	- git : https://github.com/rootsj
- 채진재 (CSS Director, Subject Proposer, Profile Function Manager)
	- git : https://github.com/jinjaeCHAE
- 남기은 (CSS Director, Favorite Function Manager)
	- git : https://github.com/pure-teatree

# Subject of service
- SNS web service based on image memes


# Requirements of service
- (Page One) Main Page<br>
	- Logo : Move to mainpage if it has been clicked<br>
	- Search Bar : Search pictures based on tags<br>
	- Log In : Move to login page<br>
	- Image and Image Click (Paging & Infinite loading, order by Trend) : Move to page that it has been posted <br>

- (Page Two) Main Page<br>
	- Upload : Move to upload page if it is clicked when only if the user has logged in <br>
	- Mypage : Move to mypage<br>
	- Log Out : Move to main page after log out<br>

- (Page Three) Main Page<br>
	- Search keywords and counting amount of resulting : show keywords and counting amount<br>

- (Page Four) Log In Page<br>
	- Log In : Move to main page after input user email and password<br>
	- Sign Up : Move to sign up page<br>

- (Page Five) Sign Up Page<br>
	- Sign Up : Move to main page after input email, nickname, password. if there have same email, then sign up button will be deactivated<br>

- (Page Six) Upload Page<br>
	- Image Upload : Get Image if it has been clicked<br>
	- Post : Move to main page after input tags and pictures<br>

- (Page Seven) My Page<br>
	- Show Profile Inform : Show information that has been updated by user<br>
	- Show Favorites : Show pictures that has been selected by user<br>
	- Update Profile Inform : Move to profile update page if it has been clicked<br>
	- Click Image : Move to post that linked with picture<br>
	- Show Follow : Show user that you followed<br>
	- (Extension function) Search : Search only on my posts <br>

- (Page Eight) Favorite page<br>
	- Click Image : Move to post that linked with picture<br>
	- (Extension function) Search : Search only on my posts<br>

- (Page Nine) Update Profile Inform<br>
	- Update Inform : Move to my page after input user inform<br>
	- Update Sns Links : Move to my page after input SNS links<br>
	- Delete Account : Move to main page after delete account<br>

- (Page Ten) Other Users Post <br>
	- Follow Button : Follow user that linked with picture<br>
	- Like Button : Increase amount of 'LIKE' that linked with picture after it has been clicked <br>
	- Favotrite Button : Register favorite picture <br>
	- Download Button : Download picture <br>
	- Copy Link Button : Copy link<br>
	- Show Like : Show amount of 'LIKE' that linked with picture <br>

# FrontEnd Structure 
Because of spirit of adventure, we have used MPA structure with Vue_CLI single file component
- Single File Components

![FrontEnd](https://github.com/byungs2/IMG/blob/master/VueComp.png)

- MPA structure

![FrontEnd](https://github.com/byungs2/IMG/blob/master/VueMPA.png)

# Skill set using in frontend
- Vue CLI
- Axios
- JS


# BackEnd Structure
![BackEnd](https://github.com/byungs2/IMG/blob/master/SpringBootStructure.png)

# Relations of DataBase
![BackEnd](https://github.com/byungs2/IMG/blob/master/data_base.png)

# Skill set using in backend
- Framework : Spring boot
- Spring JPA
- MVC pattern
- Oracle DB
- Slf4j
- Maven
- Java 8
- Rest Contoller


- methods to fix error 
	- addCorsMappings
	- 

- Testing Tool
	- PostMan
	- Swagger

# Implemented functions
- Main Page

![FrontEnd](https://github.com/byungs2/IMG/blob/master/Finpicmain.png)
![FrontEnd](https://github.com/byungs2/IMG/blob/master/allView.png)

- Sign Up

![FrontEnd](https://github.com/byungs2/IMG/blob/master/signup.png)

- Log In

![FrontEnd](https://github.com/byungs2/IMG/blob/master/login.png)

- MyPage

![FrontEnd](https://github.com/byungs2/IMG/blob/master/Otherspage.png)
![FrontEnd](https://github.com/byungs2/IMG/blob/master/SearchInmyPage.png)
![FrontEnd](https://github.com/byungs2/IMG/blob/master/mypage.png)
![FrontEnd](https://github.com/byungs2/IMG/blob/master/mypost.png)
![FrontEnd](https://github.com/byungs2/IMG/blob/master/otheronespage.png)
![FrontEnd](https://github.com/byungs2/IMG/blob/master/followList.png)

- Upload

![FrontEnd](https://github.com/byungs2/IMG/blob/master/FinpicUpload.png)
![FrontEnd](https://github.com/byungs2/IMG/blob/master/FinUploadDog.png)

- Like & Report & Favorite

![FrontEnd](https://github.com/byungs2/IMG/blob/master/LikeReportFavorite.png)

- Tag

![FrontEnd](https://github.com/byungs2/IMG/blob/master/SearchInmyPage.png)

- Search based on Tags

![FrontEnd](https://github.com/byungs2/IMG/blob/master/searchPic.png)

- Delete picture

![FrontEnd](https://github.com/byungs2/IMG/blob/master/deletePic.png)

# Teamwork Tools

- GitHub
	- issue
	- project
	- pull request
- ESLint
- Kakao Oven
- Zoom
- https://whereby.com/
- https://codeshare.io/
- 

# Using board on Github

![board](https://github.com/byungs2/IMG/blob/master/board.png)


# Notice Document for Team

# swagger API 문서화
서버 실행 후 http://127.0.0.1/swagger-ui.html 접속<br>
controller기능, 내용 확인 및 작업 가능<br>

# pom.xml

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
		</dependency
		
		<!-- Base64로 변환하기 위한 api들을 import하는 종속성 -->
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>1.4</version>
		</dependency>

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

# Vue Setting
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
    

