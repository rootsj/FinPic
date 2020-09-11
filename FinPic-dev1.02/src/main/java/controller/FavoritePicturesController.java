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

import favorite.picturesDTO.FavoritePictures;
import picturesDTO.Pictures;
import repositories.FavoritePicturesRepository;
import repositories.PicturesRepository;
import repositories.UsersRepository;
import usersDTO.Users;

@RestController
public class FavoritePicturesController {
	private final FavoritePicturesRepository favoriteRepository;
	private final PicturesRepository pictureRepository;
	private final UsersRepository usersRepository;

	FavoritePicturesController(FavoritePicturesRepository favoriteRepository, PicturesRepository pictureRepository,
			UsersRepository usersRepository) {
		this.favoriteRepository = favoriteRepository;
		this.pictureRepository = pictureRepository;
		this.usersRepository = usersRepository;
	}

	// 해당하는 유저의 즐겨찾기에 저장
	@PostMapping("/favorite/{userEmail}/{pictureNumber}")
	public void newFavorite(@PathVariable String userEmail, @PathVariable long pictureNumber) {
		Users userId = usersRepository.findByUserEmail(userEmail);
		Pictures pictureId = pictureRepository.findById(pictureNumber).orElseThrow(null);
		//null값 아닐때
		if(userId != null && pictureId != null){
			FavoritePictures favorite = favoriteRepository.findByPictureId(pictureId);
			//중복저장 안되도록
			if(favorite == null) {
				FavoritePictures newFavorite = FavoritePictures.builder().pictureId(pictureId).userId(userId).build();
				favoriteRepository.save(newFavorite);
			}
			
		}
		
	}

	// 유저 한 명의 즐겨찾기 전체 보여 주기
	@GetMapping("/favorite/{userNumber}")
	public Map<String, Object> favoriteAll(@PathVariable long userNumber) throws IOException {
		List<FavoritePictures> favoriteList = favoriteRepository.findByUserId(usersRepository.findById(userNumber).orElseThrow(() -> null));
		Map<String, Object> resultMap = new HashMap<>();
		List<String> resultBase64 = new ArrayList<>();
		List<String> pictureNumberList = new ArrayList<>();
		List<Pictures> pictureObject = new ArrayList<>();
		for (FavoritePictures favorite : favoriteList) {
			String fileName = String.valueOf(favorite.getPictureId().getPictureNumber());
			File file = new File("C:/OpenPose/FinPic/img/pictures/");
			File files [] = file.listFiles();
			for(File j : files) {
				String fileExtention = j.getName().substring(j.getName().lastIndexOf(".")+1);
				if(j.getName().equals(fileName+"."+fileExtention)) {
					//base64로 변환하는 부분
					FileInputStream in = new FileInputStream(j);
					byte bytes[] = new byte[(int)j.length()];
					in.read(bytes);
					String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
					pictureObject.add(favorite.getPictureId());
					pictureNumberList.add(fileName);
					resultBase64.add("data:image/"+fileExtention+";base64,"+encodedfile);
				}
			}
		}
		resultMap.put("pictureObject",pictureObject);
		resultMap.put("pictureNumberList", pictureNumberList);
		resultMap.put("img", resultBase64);
		return resultMap;
	}

	// 즐겨찾기 삭제
	// Picture Number로 Picture 객체 찾고 찾은 Picture 객체로 favoritePicture 객체 찾아서 삭제
	//내 아이디와 pictureNumber
	@DeleteMapping("/favorite/{userNumber}/{pictureNumber}")
	public void deleteFavorite(@PathVariable long userNumber, @PathVariable Long pictureNumber) {
		Pictures pictureId = pictureRepository.findById(pictureNumber).orElseThrow(() -> null);
		Users userId = usersRepository.findById(userNumber).orElseThrow(() -> null);
		FavoritePictures favorite = favoriteRepository.findByUserIdAndPictureId(userId, pictureId);
		favoriteRepository.delete(favorite);
	}

	// 확장기능.즐겨찾기 내에서 검색 (태그검색.picture)
	// 확장기능.카테고리 저장

}
