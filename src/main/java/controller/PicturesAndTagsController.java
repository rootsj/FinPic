package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pictures.and.tagsDTO.PicturesAndTags;
import picturesDTO.Pictures;
import repositories.PicturesAndTagsRepository;
import repositories.PicturesRepository;
import repositories.TagsRepository;
import repositories.UsersRepository;
import tagsDTO.Tags;

@RestController
public class PicturesAndTagsController {
	  private final PicturesAndTagsRepository repository;
	  private final PicturesRepository pictureRepository;
	  private final TagsRepository tagRepository;
	  private final UsersRepository userRepository;
	  
	  PicturesAndTagsController(PicturesAndTagsRepository repository, PicturesRepository pictureRepository, TagsRepository tagRepository,UsersRepository userRepository ) {
	    this.repository = repository;
	    this.pictureRepository = pictureRepository;
	    this.tagRepository = tagRepository;
	    this.userRepository = userRepository;
	  }
	  
	  //한번에 사진 여러장 올리기 추가 예정
	  //Tags, Pictures Mapping
	  //Picture와 Tag가 생성되는 시점에 Picture와 tag 사이의 관계가 함께 매칭되도록 하려면?
	  //현재는 Tags,Pictures,PicturesAndTags,Users Repository 모두 한 controller에서 사용하는 방법으로 해결해 놓았다 다른 방법이 있을까?
	  @PostMapping("/pictures-and-tags/{userNumber}")
	  public void newPicturesAndTags(@RequestParam("tagName") String tagName, @RequestParam("img") MultipartFile img, @PathVariable long userNumber) {
		  Pictures pictureId = null;
		  Date registerDate = new Date();
		  String tagNameList []  = tagName.replaceAll("\\p{Z}", "").split("#");
		  
		  //Picture logic
		  try {
			  Pictures newPicture = Pictures.builder().uploadDate(registerDate).userId(userRepository.findById(userNumber).orElseThrow(()-> null)).build();
			  img.transferTo(new File("C:/somewhere/"+newPicture.getPictureNumber()));
			  pictureId = newPicture;
			  pictureRepository.save(newPicture);
		  } catch (IllegalStateException | IOException e) {
			  e.printStackTrace();
		  }
		  
		  //Tag logic
		  for(int i = 1; i<tagNameList.length;i++) {
			  Tags tag = tagRepository.findByTagName(tagNameList[i]);
			  if(tag == null) {
				  Tags newTag = Tags.builder().tagName(tagNameList[i]).registerDate(registerDate).trendCounter(0).build();
				  tag = newTag;
				  tagRepository.save(newTag);
			  }else {
				  tag.setRegisterDate(registerDate);
				  tag.setTrendCounter(tag.getTrendCounter() + 1);
				  tagRepository.save(tag);
			  }
			  //Pictures Entity와 Tags Entity 관계 매칭
			  repository.save(PicturesAndTags.builder().pictureId(pictureId).tagId(tag).build());
		  }
	  }
	  
	  
	  //tagName이 포함된 모든 사진 불러오는 기능
	  //여러개의 태그를 동시에 검색하는 기능도 추가 예정
	  //좋아요 추가되면 좋아요 개수로 정렬하여 출력
	  @GetMapping("/pictures-and-tags/{tagName}")
	  public List<Long> getPicturesByTagName(@PathVariable String tagName) {
		  //picture가 저장된 디렉토리로 찾아가기 위한 pictureNumber들을 저장하기 위한 빈 배열 객체
		  ArrayList<Long> pictureNumberList = new ArrayList<>();
		  
		  //tagName으로 검색한 tag객체를 이용하여 PictureAndTags 객체로 이루어진 리스트 생성
		  List<PicturesAndTags> picturesAndTagslist = repository.findByTagId(tagRepository.findByTagName(tagName));
		  
		  //PicturesAndTags 리스트 for문을 돌리며 각 객체의 picture 객체에 저장된 pictureNumber 추출 후 저장
		  for(PicturesAndTags i : picturesAndTagslist) {
			  pictureNumberList.add(i.getPictureId().getPictureNumber());
		  }
		  return pictureNumberList;
	  }
	  
	  //
}
