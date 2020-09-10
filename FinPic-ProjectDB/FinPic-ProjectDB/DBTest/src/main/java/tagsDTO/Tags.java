package tagsDTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pictures.and.tagsDTO.PicturesAndTags;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Component
public class Tags {
	@Id
	@GeneratedValue(generator = "Tags_seq")
	@SequenceGenerator(name = "Tags_seq", sequenceName = "Tags_seq", allocationSize = 1)
	long tagNumber;
	String tagName;
	Date registerDate;
	
	//PicturesAndTags Entity와 양방향관계 매핑 변수명이 Pictures Entity와 같아서 고민 필요
	@OneToMany(mappedBy = "tagId")
	List<PicturesAndTags> picturesAndTags;
	
	public Tags(long tagNumber, String tagName, Date registerDate, List<PicturesAndTags> picturesAndTags) {
		this.tagNumber = tagNumber;
		this.tagName = tagName;
		this.registerDate = registerDate;
		this.picturesAndTags = picturesAndTags;
	}
}
