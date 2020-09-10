package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	  @PostMapping("/pictures-and-tags")
	  public void newPicturesAndTags(@RequestParam("tagName") String tagName, @RequestParam("img") MultipartFile img, @ModelAttribute("userNumber") long userNumber) {
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
				  Tags newTag = Tags.builder().tagName(tagNameList[i]).registerDate(registerDate).build();
				  tag = newTag;
				  tagRepository.save(newTag);
			  }else {
				  tag.setRegisterDate(registerDate);
				  tagRepository.save(tag);
			  }
			  //Pictures Entity와 Tags Entity 관계 매칭
			  repository.save(PicturesAndTags.builder().pictureId(pictureId).tagId(tag).build());
		  }

	  }
	  
	  
	  
	  //tagName이 포함된 모든 사진 불러오는 기능
	  //여러개의 태그를 동시에 검색하는 기능도 추가 예정
	  @GetMapping("/pictures-and-tags")
	  public String getPicturesByTagName() {
		  
		  return null;
	  }
}
