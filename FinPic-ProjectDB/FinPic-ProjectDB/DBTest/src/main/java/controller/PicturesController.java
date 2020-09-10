package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import picturesDTO.Pictures;
import repositories.PicturesRepository;
import repositories.UsersRepository;

@RestController
public class PicturesController {
	  private final PicturesRepository repository;
	  private final UsersRepository userRepository;
	  
	  PicturesController(PicturesRepository repository, UsersRepository userRepository) {
	    this.repository = repository;
	    this.userRepository = userRepository;
	  }
	  //모든 picture 검색
	  @GetMapping("/pictures")
	  public List<Pictures> getAll() {
		  return repository.findAll();
	  }
	  
	  //Picture 객체 저장
	  //실제로 사용되는 url이 아닌 단순 테스트용으로 실제 사용은 PicturesAndTagsController에서 담당
	  @PostMapping("/pictures")
	  public String uploadPicture(@RequestParam("img") MultipartFile img, @ModelAttribute("userNumber") long userNumber) {
		  Date uploadDate = new Date();
		  try {
			Pictures newPicture = Pictures.builder().uploadDate(uploadDate).userId(userRepository.findById(userNumber).orElseThrow(()-> null)).build();
			img.transferTo(new File("C:/somewhere/"+newPicture.getPictureNumber()));
			repository.save(newPicture);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		  return "forward:/something.jsp";
	  }
	  
	  //Picture 삭제 (본인이 올린 사진만)
	  //한번에 여러개 삭제하는 기능 추가 예정
	  //관리자 기능 혹은 신고 기능으로 삭제하는 기능 추가 예정 
	  @DeleteMapping("/pictures/{pictureNumber}")
	  public String deletePicture(@PathVariable long pictureNumber) {
		  Pictures picture = repository.findById(pictureNumber).orElseThrow(()->null);
		  repository.delete(picture);
		  return "redirect:/something.jsp";
	  }
	  
	  //Picture 수정
}
