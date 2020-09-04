package kr.pe.khk.service;

import org.springframework.stereotype.Service;

import kr.pe.khk.dto.User;

@Service
public class UserService {
	
	//db 연동없이 test 용으로 검증해주는 메소드
	public User signin(String email, String password) {
		
		if(email.equals("a@b.com") && password.equals("11")) {
			return new User(email, password);
		}else {
			throw new RuntimeException("누구?");
		}
	}
	
	public String getServletInfo() {
		return "khk";
	}
}
