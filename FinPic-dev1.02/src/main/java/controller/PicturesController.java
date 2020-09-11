package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	  public void deletePicture(@PathVariable long pictureNumber) {
		  Pictures picture = repository.findById(pictureNumber).orElseThrow(()->null);
		  String fileName = String.valueOf(pictureNumber);
<<<<<<< HEAD:FinPic-dev1.02/src/main/java/controller/PicturesController.java
		  File file = new File("C:/OpenPose/FinPic/img/pictures/");
=======
		  File file = new File("C:/FinIMG/");
>>>>>>> a194a3129057d8692cbbb1ba310fe856ac812fd9:src/main/java/controller/PicturesController.java
		  File files [] = file.listFiles();
		  for(File i : files) {
			  String fileExtention = i.getName().substring(i.getName().lastIndexOf(".")+1);
			  if(i.getName().equals(fileName+"."+fileExtention)) {
				  i.delete();
			  }
		  }
		  repository.delete(picture);
	  }
	  
	  //userNumber로 Picture 검색(즉 My page에서 내가 올린 사진 출력 기능 담당)
	  @GetMapping(value = "/pictures/{userNumber}")
	  public Map<String, Object> getUserPictures(@PathVariable long userNumber) throws IOException{
		  Users user = userRepository.findById(userNumber).orElseThrow(()->null);
		  List<Pictures> pictures = repository.findByUserId(user);
		  Map<String, Object> resultMap = new HashMap<>();
		  List<String> resultBase64 = new ArrayList<>();
		  List<String> pictureNumberList = new ArrayList<>();
		  List<Pictures> pictureObject = new ArrayList<>();
		  for(Pictures i : pictures) {
			  //확장자 명을 동적으로 설정해야한다 어떻게 할까
			  //for문을 두번 쓰는 것으로 해결해 놓았다. 다른 방법은 없을까
			  String fileName = String.valueOf(i.getPictureNumber());
<<<<<<< HEAD:FinPic-dev1.02/src/main/java/controller/PicturesController.java
			  File file = new File("C:/OpenPose/FinPic/img/pictures/");
=======
			  File file = new File("C:/FinIMG/");
>>>>>>> a194a3129057d8692cbbb1ba310fe856ac812fd9:src/main/java/controller/PicturesController.java
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
					  pictureNumberList.add(fileName);
					  pictureObject.add(i);
				  }
			  }
		  }
		  resultMap.put("pictureObject",pictureObject);
		  resultMap.put("pictureNumberList", pictureNumberList);
		  resultMap.put("img", resultBase64);
		  return resultMap;
	  }
	  
	  //모든 사진 반환
	  @GetMapping(value = "/all-pictures")
	  public Map<String, Object> getAllPictures() throws IOException{
		  Map<String, Object> resultMap = new HashMap<>();
		  List<String> resultBase64 = new ArrayList<>();
		  List<String> pictureNumberList = new ArrayList<>();
		  List<Pictures> allPicturesObject = repository.findAll();
<<<<<<< HEAD:FinPic-dev1.02/src/main/java/controller/PicturesController.java
			  File file = new File("C:/OpenPose/FinPic/img/pictures/");
=======
			  File file = new File("C:/FinIMG/");
>>>>>>> a194a3129057d8692cbbb1ba310fe856ac812fd9:src/main/java/controller/PicturesController.java
			  File files [] = file.listFiles();
			  for(File j : files) {
				  String fileExtention = j.getName().substring(j.getName().lastIndexOf(".")+1);
				  pictureNumberList.add(j.getName().substring(0, j.getName().lastIndexOf(".")));
				  FileInputStream in = new FileInputStream(j);
				  byte bytes[] = new byte[(int)j.length()];
				  in.read(bytes);
				  String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
				  //Vue에서 알아먹을 수 있도록 확장자명과 파일 이름을 넘겨준다
				  resultBase64.add("data:image/"+fileExtention+";base64,"+encodedfile);
			  }
			  resultMap.put("pictureObject", allPicturesObject);
			  resultMap.put("pictureNumberList", pictureNumberList);
			  resultMap.put("img", resultBase64);
			  return resultMap;
	  }
	  
	  //사진 다운로드
	  //프론트에서 사진 날짜별 정렬 후 렌더링
	  
		// 사진 한장 가져오기
		@GetMapping(value = "/post/{pictureNumber}")
		public Map<String, Object> getPicture(@PathVariable long picturesNumber) throws IOException{
			Map<String, Object> resultMap = new HashMap<>();
			String pictureImgBase64 = null;
			String pictureNumber = null;
			Pictures picture = repository.findById(picturesNumber).orElseThrow(null);
			File file = new File("C:/OpenPose/FinPic/img/pictures/");
			File files[] = file.listFiles();
			for (File f : files) {
				String fileExt = f.getName().substring(f.getName().lastIndexOf(".") + 1);
				if (f.getName().equals(String.valueOf(picturesNumber) + "." + fileExt)) {
					pictureNumber = f.getName().substring(0, f.getName().lastIndexOf("."));
					FileInputStream in = new FileInputStream(f);
					byte bytes[] = new byte[(int) f.length()];
					in.read(bytes);
					String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
					pictureImgBase64 = "data:image/" + fileExt + ";base64," + encodedfile;
					resultMap.put("pictureObject", picture);
					resultMap.put("pictureNumberList", pictureNumber);
					resultMap.put("img", pictureImgBase64);
					in.close();
					return resultMap;
				}
			}
			return null;
		}
}
