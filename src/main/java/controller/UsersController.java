package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import repositories.UsersRepository;
import service.JwtService;
import usersDTO.Users;

@RestController
//@CrossOrigin("http://localhost:8081")
public class UsersController {
	private final UsersRepository repository;
	
	@Autowired
	private JwtService jwtService;
	
	UsersController(UsersRepository repository) {
		this.repository = repository;
	}
	
	// 모든 회원 검색
	@GetMapping("/users")
	public List<Users> getAll() {
		return repository.findAll();
	}

	// 회원 가입
	@PostMapping("/users")
	public String newUser(@RequestBody Users newUser) {
		System.out.println("SignIn");
		// 회원가입 시 동일 이메일을 가진 정보가 있는지 확인
		Date userRegisterDate = new Date();
		Users userByEmail = repository.findByUserEmail(newUser.getUserEmail());
		Users userByName = repository.findByUserName(newUser.getUserName());
		if (userByEmail != null || userByName != null) {
			return "redirect:/something1.jsp";
		} else {
			newUser.setUserRegisterDate(userRegisterDate);
			repository.save(newUser);
			return "redirect:/something.jsp";
		}
	}

	// 회원 탈퇴
	@DeleteMapping("/users/{userNumber}")
	public String userDelete(@PathVariable long userNumber) {
		// 예외처리 추가해야 Optional 안씀 null 부분에 예외처리 객체 추가하자
		Users user = repository.findById(userNumber).orElseThrow(() -> null);
		
		repository.delete(user);
		return "redirect:/somewhere.jsp";
	}

	// 로그인
	@GetMapping("/users/login")
	public Map<String, Object> userLogIn(HttpServletResponse res, @RequestParam("userEmail") String userEmail,
			@RequestParam("userPassword") String userPassword) {
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("LogIn");
		// 입력받은 userEmail과 userPassword로 회원정보 검색
		Users user = repository.findByUserEmailAndUserPassword(userEmail, userPassword);
		if (user != null) {
			//token 생성
			String token = jwtService.create(user);
			
			res.setHeader("jwt-auth-token", token);
			resultMap.put("auth_token", token);
			resultMap.put("status", true);
			resultMap.put("data", user);
		} else {
			// error advice or Exception 객체 생성하여 해당 error advice 객체에서 endview로 redirect or
			// forward로 이동하는 방법이 좋아보인다.
		}
		return resultMap;
	}

	//로그아웃 기능은 포른트엔드에서 처리
	
	// 닉네임으로 회원 한명 검색
	@GetMapping("/users/{userName}")
	public Users getOneUser(@PathVariable String userName) {
		return repository.findByUserName(userName);
	}
	
	//비밀번호 맞는지 검증
	@PostMapping("/users/verification/{userNumber}")
	public boolean verifyUser(@RequestParam("userPassword") String userPassword, @PathVariable long userNumber) {
		Users user = repository.findById(userNumber).orElseThrow(() -> null);
		if(user.getUserPassword().equals(userPassword)) {
			return true;
		}
		return false;
	}
	
	//닉네임 혹은 패스워드 변경
	@PutMapping("/users/{userNumber}")
	public String updateUser(@RequestParam(required = false,value = "userName") String userName, @RequestParam(required = false, value = "userPassword") String userPassword, @PathVariable long userNumber) {
		Users user = repository.findById(userNumber).orElseThrow(() -> null);
		
		if(userName != null && userName.length() != 0) {
			user.setUserName(userName);
		}
		if(userPassword != null && userPassword.length() != 0) {
			user.setUserPassword(userPassword);
		}
		repository.save(user);
		return "redirect:/somewhere.jsp";
	}
	//관리자 기능 - 강퇴기능/정지 기능/닉네임 강제 변경/패스워드 재설정/휴면 계정 처리/
	
}
