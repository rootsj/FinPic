package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
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
import usersDTO.Users;

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
	  
	  //Picture 삭제 (본인이 올린 사진만)
	  //한번에 여러개 삭제하는 기능 추가 예정
	  //관리자 기능 혹은 신고 기능으로 삭제하는 기능 추가 예정 
	  @DeleteMapping("/pictures/{pictureNumber}")
	  public String deletePicture(@PathVariable long pictureNumber) {
		  Pictures picture = repository.findById(pictureNumber).orElseThrow(()->null);
		  repository.delete(picture);
		  return "redirect:/something.jsp";
	  }
	  
	  //userNumber로 Picture 검색(즉 My page에서 내가 올린 사진 출력 기능 담당)
	  @GetMapping(value = "/pictures/{userNumber}")
	  public List<String> getUserPictures(@PathVariable long userNumber) throws IOException{
		  Users user = userRepository.findById(userNumber).orElseThrow(()->null);
		  List<Pictures> pictures = repository.findByUserId(user);
		  List<String> resultBase64 = new ArrayList<>();
		  
		  for(Pictures i : pictures) {
			  //확장자 명을 동적으로 설정해야한다 어떻게 할까
			  //for문을 두번 쓰는 것으로 해결해 놓았다. 다른 방법은 없을까
			  String fileName = String.valueOf(i.getPictureNumber());
			  File file = new File("H:/FinIMG/");
			  File files [] = file.listFiles();
			  for(File j : files) {
				  String fileExtention = j.getName().substring(j.getName().lastIndexOf(".")+1);
				  if(j.getName().equals(fileName+"."+fileExtention)) {
					  FileInputStream in = new FileInputStream(j);
					  byte bytes[] = new byte[(int)j.length()];
					  in.read(bytes);
					  String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
					  //Vue에서 알아먹을 수 있도록 확장자명과 파일 이름을 넘겨준다
					  resultBase64.add("data:image/"+fileExtention+";base64,"+encodedfile);
				  }
			  }
		  }
		  return resultBase64;
	  }
	  
	  //모든 사진 반환
	  @GetMapping(value = "/all-pictures")
	  public List<String> getAllPictures() throws IOException{
		  List<String> resultBase64 = new ArrayList<>();
			  File file = new File("H:/FinIMG/");
			  File files [] = file.listFiles();
			  for(File j : files) {
				  String fileExtention = j.getName().substring(j.getName().lastIndexOf(".")+1);
				  FileInputStream in = new FileInputStream(j);
				  byte bytes[] = new byte[(int)j.length()];
				  in.read(bytes);
				  String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
				  //Vue에서 알아먹을 수 있도록 확장자명과 파일 이름을 넘겨준다
				  resultBase64.add("data:image/"+fileExtention+";base64,"+encodedfile);
			  }
		 
		  return resultBase64;
	  }
	  
	  //사진 다운로드
	  //프론트에서 사진 날짜별 정렬 후 렌더링
}
