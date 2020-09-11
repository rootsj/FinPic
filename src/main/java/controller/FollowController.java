package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import followDTO.Follow;
import repositories.FollowRepository;
import repositories.UsersRepository;
import usersDTO.Users;

@RestController
public class FollowController {

	
	private final UsersRepository usersRepository;
	private final FollowRepository followRepository;

	FollowController(UsersRepository usersRepository, FollowRepository followRepository) {
		this.usersRepository = usersRepository;
		this.followRepository = followRepository;
	}

	//팔로우 등록
	@PostMapping("/follow/{userNumber}/{userEmail}")
	public boolean follow(@PathVariable long userNumber, @PathVariable String userEmail) {
		Users fromUser = usersRepository.findById(userNumber).orElseThrow(null);
		Users toUser = usersRepository.findByUserEmail(userEmail);

		if(!followRepository.existsByFromUserAndToUser(fromUser, toUser) & (fromUser != toUser)) {
			Follow follow = new Follow();
			follow.setFromUser(fromUser);
			follow.setToUser(toUser);
			
			followRepository.save(follow);
			return followRepository.existsByFromUserAndToUser(fromUser, toUser);
		} else {
			return false;
		}

	}
	
	
	//팔로우 삭제
	@DeleteMapping("/follow/{userNumber}/{userEmail}")
	public boolean unFollow(@PathVariable long userNumber, @PathVariable String userEmail) {
		Users fromUser =  usersRepository.findById(userNumber).orElseThrow(null);
		Users toUser = usersRepository.findByUserEmail(userEmail);
		followRepository.deleteByFromUserAndToUser(fromUser, toUser);

		return followRepository.existsByFromUserAndToUser(fromUser, toUser);
	}

	   //내가 팔로우한사람 리스트 출력
	   @GetMapping("/follow/follow/{userNumber}")
	   public Map<String, Object> findFollowers(@PathVariable long userNumber) throws IOException {
	      Map<String, Object> resultMap = new HashMap<>();
	      Users fromUser = usersRepository.findById(userNumber).orElseThrow(null);
	      List<String> resultBase64 = new ArrayList<>();
	      List<Follow> follows = followRepository.findByFromUser(fromUser);
	      File file = new File("C:/OpenPose/FinPic/img/users/");
	      File files [] = file.listFiles();
	      for(Follow i : follows) {
	         String fileName = i.getToUser().getUserEmail();
	         for(File j : files) {
	            String fileExtention = j.getName().substring(j.getName().lastIndexOf(".")+1);
	            if(j.getName().substring(0, j.getName().lastIndexOf(".")).equals(fileName)) {
	                 FileInputStream in = new FileInputStream(j);
	                 byte bytes[] = new byte[(int)j.length()];
	                 in.read(bytes);
	                 String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	                 resultBase64.add("data:image/"+fileExtention+";base64,"+encodedfile);
	            }
	         }
	      }
	      resultMap.put("data", follows);
	      resultMap.put("img", resultBase64);
	      return resultMap;
	   }

	//내가 팔로우한 사람 카운팅
	@GetMapping("/follow/count/{userNumber}")
	public long countFromUser(@PathVariable long userNumber) {
		Users fromUser = usersRepository.findById(userNumber).orElseThrow(null);
		return followRepository.countByFromUser(fromUser);
	}

	//나를 팔로우한 사람 수
	public long countToUser(String userEmail) {
		Users toUser = usersRepository.findByUserEmail(userEmail);
		return followRepository.countByToUser(toUser);
	}
	
	//
	//팔로우 유무 확인
	@GetMapping("/follow/check/{userNumber}/{userEmail}")
	public boolean checkFollow(@PathVariable long userNumber, @PathVariable String userEmail) {
	    Users fromUser = usersRepository.findById(userNumber).orElseThrow(null);
	    Users toUser = usersRepository.findByUserEmail(userEmail);
	    System.out.println(followRepository.existsByFromUserAndToUser(fromUser, toUser));
	    return followRepository.existsByFromUserAndToUser(fromUser, toUser);
	 }
}
