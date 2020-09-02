package picturesDTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import favorite.picturesDTO.FavoritePictures;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pictures.and.tagsDTO.PicturesAndTags;
import usersDTO.Users;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Component
public class Pictures {
	@Id
	@GeneratedValue(generator = "Pictures_seq")
	@SequenceGenerator(name = "Pictures_seq", sequenceName = "Pictures_seq", allocationSize = 1)
	long pictureNumber;
	
	Date uploadDate;
	
	@ManyToOne
	@JoinColumn(name = "USERS_ID")
	Users userId;
	
	//PicturesAndTags Entity와 양방향관계 Mapping
	@OneToMany(mappedBy = "pictureId")
	List<PicturesAndTags> picturesAndTags;
	
	//FavoritePictures Entity와 양방향관계 Mapping
	@OneToMany(mappedBy = "pictureId")
	List<FavoritePictures> favoritePictures;
	
	public Pictures(long pictureNumber, Date uploadDate, Users userId, List<PicturesAndTags> picturesAndTags, List<FavoritePictures> favoritePictures) {
		this.pictureNumber = pictureNumber;
		this.uploadDate = uploadDate;
		this.userId = userId;
		this.picturesAndTags = picturesAndTags;
		this.favoritePictures = favoritePictures;
	}
	

}
