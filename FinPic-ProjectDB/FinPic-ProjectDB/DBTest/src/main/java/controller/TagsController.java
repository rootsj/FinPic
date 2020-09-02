package controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import repositories.TagsRepository;
import tagsDTO.Tags;

@RestController
public class TagsController {
	  private final TagsRepository repository;
	  TagsController(TagsRepository repository) {
	    this.repository = repository;
	  }
	  //모든 태그 보기
	  @GetMapping("/tags")
	  public List<Tags> getAll(){
		  return repository.findAll();
	  }
	  
	  //tagName이 새로운 것일 때 추가하는 기능
	  //이때 RegisterDate는 항상 갱신된다 
	  //저장 및 갱신시 null값 저장  문제 발생//배열 맨앞을 참조하지 않는 것으로 임시 조치 해놓았음
	  //실제로 사용되는 url이 아닌 단순 테스트용으로 실제 사용은 PicturesAndTagsController에서 담당
	  @PostMapping("/tags")
	  public String newTag(@RequestParam("tagName") String tagName) {
		  Date registerDate = new Date();
		  String tagNameList []  = tagName.replaceAll("\\p{Z}", "").split("#");
		  for(int i = 1; i<tagNameList.length;i++) {//i = 1에서부터 시작해서 배열 맨앞의 null을 참조하지 않는다
			  Tags tag = repository.findByTagName(tagNameList[i]);
			  if(tag == null) {
				  Tags newTag = Tags.builder().tagName(tagNameList[i]).registerDate(registerDate).build();
				  repository.save(newTag);
			  }else {
				  tag.setRegisterDate(registerDate);
				  repository.save(tag);
			  }
		  }
		  return "well";
	  }
	  
	  
	  //Containing function testing method
	  @GetMapping("/tagstest")
	  public List<Tags> tags(@RequestParam("tagName") String tagName){
		  return repository.findByTagNameContaining(tagName);
	  }
	  
	  
	  //tagRegisterDate가 24시간 이내인 경우 트렌드에 출력
	  
	  
	  
}
