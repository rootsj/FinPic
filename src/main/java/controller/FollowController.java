package controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public @ResponseBody String follow(@PathVariable long userNumber, @PathVariable String userEmail) {
		Users fromUser = usersRepository.findById(userNumber).orElseThrow(null);
		Users toUser = usersRepository.findByUserEmail(userEmail);

		if(!followRepository.existsByFromUserAndToUser(fromUser, toUser) & (fromUser != toUser)) {
			Follow follow = new Follow();
			follow.setFromUser(fromUser);
			follow.setToUser(toUser);
			
			followRepository.save(follow);
			return "팔로우 완료";
		} else {
			return "팔로우 error";
		}

	}
	
	
	//팔로우 삭제
	@DeleteMapping("/follow/{userNumber}/{userEmail}")
	public @ResponseBody String unFollow(@PathVariable long userNumber, @PathVariable String userEmail) {
		Users fromUser =  usersRepository.findById(userNumber).orElseThrow(null);
		Users toUser = usersRepository.findByUserEmail(userEmail);
		followRepository.deleteByFromUserAndToUser(fromUser, toUser);

		return "팔로우 삭제";
	}

	//내가 팔로우한사람 리스트 출력
	@GetMapping("/follow/follow/{userNumber}")
	public List<Follow> findFollowers(@PathVariable long userNumber) {
		Users fromUser = usersRepository.findById(userNumber).orElseThrow(null);
		List<Follow> follows = followRepository.findByFromUser(fromUser);
		return follows;
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
	
	//팔로우 유무 확인
	@GetMapping("/follow/check/{userNumber}/{userEmail}")
	public boolean checkFollow(@PathVariable long userNumber, @PathVariable String userEmail) {
		Users fromUser = usersRepository.findById(userNumber).orElseThrow(null);
		Users toUser = usersRepository.findByUserEmail(userEmail);
		return followRepository.existsByFromUserAndToUser(fromUser, toUser);
	}
}
