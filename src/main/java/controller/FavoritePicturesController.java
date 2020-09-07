package controller;

import java.util.ArrayList;
import java.util.List;

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
	  
	  FavoritePicturesController(FavoritePicturesRepository favoriteRepository, PicturesRepository pictureRepository, UsersRepository usersRepository) {
	    this.favoriteRepository = favoriteRepository;
	    this.pictureRepository = pictureRepository;
	    this.usersRepository = usersRepository;
	  }
	  
	  //해당하는 유저의 즐겨찾기에 저장
	  @PostMapping("/favorite/{userEmail}/{pictureNumber}")
	  FavoritePictures newFavorite(@PathVariable String userEmail, @PathVariable long pictureNumber) {
		  Users userId = usersRepository.findByUserEmail(userEmail);
		  Pictures pictureId = pictureRepository.findById(pictureNumber).orElseThrow(null);
		  FavoritePictures newFavorite = FavoritePictures.builder().pictureId(pictureId).userId(userId).build();
		  return favoriteRepository.save(newFavorite);
	  }
	  
	  //유저 한 명의 즐겨찾기 전체 보여 주기 
	  @GetMapping("/favorite/{userEmail}")
	  public List <Long> favoriteAll(@PathVariable String userEmail){
		  ArrayList<Long> pictureNumberList = new ArrayList<>();
		  List <FavoritePictures> favoriteList = favoriteRepository.findByUserId(usersRepository.findByUserEmail(userEmail));
		  for(FavoritePictures favorite:favoriteList) {
			  pictureNumberList.add(favorite.getPictureId().getPictureNumber());
		  }
		  return pictureNumberList;
	  }
	  
	  //즐겨찾기 삭제
	  @DeleteMapping("/favorite/{favoritepictureNumber}")
	  void deleteFavorite(@PathVariable Long favoritepictureNumber) {
		  FavoritePictures favorite = favoriteRepository.findById(favoritepictureNumber).orElseThrow(()->null);
		  favoriteRepository.delete(favorite);;
	  }
	  
	  
	  //확장기능.즐겨찾기 내에서 검색 (태그검색.picture)
	  //확장기능.카테고리 저장
	  
}
