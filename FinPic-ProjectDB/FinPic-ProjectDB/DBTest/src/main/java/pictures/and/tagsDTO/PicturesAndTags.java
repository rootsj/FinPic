package pictures.and.tagsDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import picturesDTO.Pictures;
import tagsDTO.Tags;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Component
public class PicturesAndTags {
	@Id
	@GeneratedValue(generator = "PicturesAndTags_seq")
	@SequenceGenerator(name = "PicturesAndTags_seq", sequenceName = "PicturesAndTags_seq", allocationSize = 1)
	long pictureandtagNumber;
	
	@ManyToOne
	@JoinColumn(name = "PICTURES_ID")
	Pictures pictureId;
	
	@ManyToOne
	@JoinColumn(name = "TAGS_ID")
	Tags tagId;

	public PicturesAndTags(long pictureandtagNumber, Pictures pictureId, Tags tagId) {
		this.pictureandtagNumber = pictureandtagNumber;
		this.pictureId = pictureId;
		this.tagId = tagId;
	}

	
}
