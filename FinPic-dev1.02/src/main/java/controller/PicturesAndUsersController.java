package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import pictures.and.usersDTO.PicturesAndUsers;
import picturesDTO.Pictures;
import repositories.PicturesAndUsersRepository;
import repositories.PicturesRepository;
import repositories.UsersRepository;
import usersDTO.Users;

//Like와 Report 기능을 수행하기 위한 컨트롤러
@RestController
public class PicturesAndUsersController {
	  private final UsersRepository userRepository;
	  private final PicturesRepository pictureRepository;
	  private final PicturesAndUsersRepository repository;
	  
	  PicturesAndUsersController(UsersRepository userRepository,PicturesRepository pictureRepository,PicturesAndUsersRepository repository){
		  this.userRepository = userRepository;
		  this.pictureRepository = pictureRepository;
		  this.repository = repository;
	  }
	  //모든 좋아요 및 신고 상태 보기
	  @GetMapping("/pictures-and-users")
	  public List<PicturesAndUsers> getAll(){
		  return repository.findAll();
	  }
	  
	  //Like
	  @PutMapping("/like/{userNumber}/{pictureNumber}")
	  public void countLike(@PathVariable long userNumber, @PathVariable long pictureNumber) {
		  Users likedUser = userRepository.findById(userNumber).orElseThrow(()->null);
		  Pictures likedPicture = pictureRepository.findById(pictureNumber).orElseThrow(()->null);
		  PicturesAndUsers picturesAndUsers = repository.findByLikedPictureIdAndLikedUserId(likedPicture, likedUser);
		  if(picturesAndUsers == null) {
			  picturesAndUsers = PicturesAndUsers.builder().likedPictureId(likedPicture).likedUserId(likedUser).build();
			  likedPicture.setLikeCounter(likedPicture.getLikeCounter() + 1);
			  pictureRepository.save(likedPicture);
			  repository.save(picturesAndUsers);
		  }else {
			  likedPicture.setLikeCounter(likedPicture.getLikeCounter() - 1);
			  pictureRepository.save(likedPicture);
			  repository.delete(picturesAndUsers);
		  }
	  }
	  
	  //Report
	  @PutMapping("/report/{userNumber}/{pictureNumber}")
	  public void countReport(@PathVariable long userNumber, @PathVariable long pictureNumber) {
		  Users reportedUser = userRepository.findById(userNumber).orElseThrow(()->null);
		  Pictures reportedPicture = pictureRepository.findById(pictureNumber).orElseThrow(()->null);
		  PicturesAndUsers picturesAndUsers = repository.findByReportedPictureIdAndReportedUserId(reportedPicture, reportedUser);
		  if(picturesAndUsers == null) {
			  picturesAndUsers = PicturesAndUsers.builder().reportedPictureId(reportedPicture).reportedUserId(reportedUser).build();
			  reportedPicture.setReportCounter(reportedPicture.getReportCounter() + 1);
			  pictureRepository.save(reportedPicture);
			  repository.save(picturesAndUsers);
		  }else {
			  reportedPicture.setReportCounter(reportedPicture.getReportCounter() - 1);
			  pictureRepository.save(reportedPicture);
			  repository.delete(picturesAndUsers);
		  }
	  }
}
