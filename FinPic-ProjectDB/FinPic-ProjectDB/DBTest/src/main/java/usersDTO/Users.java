package usersDTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import favorite.picturesDTO.FavoritePictures;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pictures.and.tagsDTO.PicturesAndTags;
import picturesDTO.Pictures;
import profilesDTO.Profiles;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Component
public class Users {
	@Id
	@GeneratedValue(generator = "Users_seq")
	@SequenceGenerator(name = "Users_seq", sequenceName = "Users_seq", allocationSize = 1)
	long userNumber;
	String userName;
	String userEmail;
	String userPassword;
	Date userRegisterDate;
	
	//Pictures Entity와 양방향 관계 Mapping
	@OneToMany(mappedBy = "userId")
	List<Pictures> pictures;
	
	//FavoritePictures Entity와 양방향 관계 Mapping
	@OneToMany(mappedBy = "userId")
	List<FavoritePictures> favoritePictures;
	
	//Profiles Entity와 일대일 양방향 관계 Mapping
	@OneToOne(mappedBy = "userId")
	Profiles profileId;
	
	
	public Users(long userNumber, String userName, String userEmail, String userPassword, Date userRegisterDate, List<Pictures> pictures, List<FavoritePictures> favoritePictures, Profiles profileId) {
		this.userNumber = userNumber;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRegisterDate = userRegisterDate;
		this.pictures = pictures;
		this.favoritePictures = favoritePictures;
		this.profileId = profileId;
	}


	
	
}
