# FinPic

# we are going to make funny webpage!

# pom.xml
<?xml version="1.0" encoding="UTF-8"?><br>
<project xmlns="http://maven.apache.org/POM/4.0.0"<br>
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"<br>
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"><br>
	<modelVersion>4.0.0</modelVersion><br>
	<parent><br>
		<groupId>org.springframework.boot</groupId><br>
		<artifactId>spring-boot-starter-parent</artifactId><br>
		<version>2.3.3.RELEASE</version><br>
		<relativePath /> <!-- lookup parent from repository --><br>
	</parent><br>
	<groupId>com.example</groupId><br>
	<artifactId>DBTest</artifactId><br>
	<version>0.0.1-SNAPSHOT</version><br>
	<packaging>war</packaging><br>
	<name>DBTest</name><br>
	<description>Demo project for Spring Boot</description><br><br>

	<properties><br>
		<java.version>1.8</java.version><br>
	</properties><br>

	<dependencies><br>
		<dependency><br>
			<groupId>org.springframework.boot</groupId><br>
			<artifactId>spring-boot-starter-data-jpa</artifactId><br>
		</dependency><br>
		<dependency><br>
			<groupId>org.springframework.boot</groupId><br>
			<artifactId>spring-boot-starter-web</artifactId><br>
		</dependency><br><br>

		<dependency><br>
			<groupId>org.springframework.boot</groupId><br>
			<artifactId>spring-boot-devtools</artifactId><br>
			<scope>runtime</scope><br>
			<optional>true</optional><br>
		</dependency><br>
		<!-- maven으로 oracle DB driver setting시 repository 설정도 해줘야한다 구글링 ㄱㄱ <dependency> <br>
			<groupId>com.oracle.database.jdbc</groupId> <artifactId>ojdbc8</artifactId> <br>
			<scope>runtime</scope> </dependency> --><br>
		<dependency><br>
			<groupId>org.projectlombok</groupId><br>
			<artifactId>lombok</artifactId><br>
			<optional>true</optional><br>
		</dependency><br>
		<dependency><br>
			<groupId>org.springframework.boot</groupId><br>
			<artifactId>spring-boot-starter-tomcat</artifactId><br>
			<scope>provided</scope><br>
		</dependency><br>
		<dependency><br>
			<groupId>org.springframework.boot</groupId><br>
			<artifactId>spring-boot-starter-test</artifactId><br>
			<scope>test</scope><br>
			<exclusions><br>
				<exclusion><br>
					<groupId>org.junit.vintage</groupId><br>
					<artifactId>junit-vintage-engine</artifactId><br>
				</exclusion><br>
			</exclusions><br>
		</dependency><br>
		<dependency><br>
			<groupId>io.jsonwebtoken</groupId><br>
			<artifactId>jjwt</artifactId><br>
			<version>0.9.1</version><br>
		</dependency><br><br>

		<!-- jdk 9 이상의 경우 xml처리를 위한 jaxb-api 필요 --><br>
		<dependency><br>
			<groupId>javax.xml.bind</groupId><br>
			<artifactId>jaxb-api</artifactId><br>
			<version>2.3.1</version><br>
		</dependency><br><br>

		<dependency><br>
			<groupId>com.jslsolucoes</groupId><br>
			<artifactId>ojdbc6</artifactId><br>
			<version>11.2.0.1.0</version><br>
		</dependency><br><br>


		<!-- jsp 사용을 위한 설정 --><br>
		<dependency><br>
			<groupId>org.apache.tomcat.embed</groupId><br>
			<artifactId>tomcat-embed-jasper</artifactId><br>
			<version>9.0.36</version><br>
		</dependency><br><br>


		<!-- jstl 라이브러리 --><br>
		<dependency><br>
			<groupId>javax.servlet</groupId><br>
			<artifactId>javax.servlet-api</artifactId><br>
			<version>3.1.0</version><br>
		</dependency><br>
		<dependency><br>
			<groupId>javax.servlet</groupId><br>
			<artifactId>jstl</artifactId><br>
		</dependency><br>
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 --><br><br>

	</dependencies><br><br>

	<build><br>
		<plugins><br>
			<plugin><br>
				<groupId>org.springframework.boot</groupId><br>
				<artifactId>spring-boot-maven-plugin</artifactId><br>
			</plugin><br>
		</plugins><br>
	</build><br><br>

</project><br>

# application.properties

server.port=80

#DataSource Setting<br>
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver<br>
spring.datasource.url=jdbc:oracle:thin:@127.0.0.1:1521:xe<br>
spring.datasource.username=SCOTT<br>
spring.datasource.password=TIGER<br>


#JPA Setting<br>
spring.jpa.hibernate.ddl-auto=create<br>
#spring.jpa.hibernate.ddl-auto=none<br>
spring.jpa.generate-ddl=false<br>
spring.jpa.show-sql=true<br>
spring.jpa.properties.hibernate.format_sql=true<br><br>
spring.jpa.database=oracle<br>
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect<br>


#Logging Setting<br>
logging.level.org.hibernate=info<br>

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
    
