package usersDTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import favorite.picturesDTO.FavoritePictures;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pictures.and.tagsDTO.PicturesAndTags;
import pictures.and.usersDTO.PicturesAndUsers;
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
	@JsonIgnore
	List<Pictures> pictures;
	
	//FavoritePictures Entity와 양방향 관계 Mapping
	@OneToMany(mappedBy = "userId")
	@JsonIgnore
	List<FavoritePictures> favoritePictures;
	
	//Profiles Entity와 일대일 양방향 관계 Mapping
	@OneToOne(mappedBy = "userId")
	Profiles profileId;
	
	//PicturesAndUsers Entity와 양방향관계 매핑 변수명이 Pictures Entity와 같아서 고민 필요
	@OneToMany(mappedBy = "likedUserId")
	@JsonIgnore
	List<PicturesAndUsers> picturesAndUsers;
	
	@OneToMany(mappedBy = "reportedUserId")
	@JsonIgnore
	List<PicturesAndUsers> reportedPicturesAndUsers;
	
	public Users(long userNumber, String userName, String userEmail, String userPassword, Date userRegisterDate, List<Pictures> pictures, List<FavoritePictures> favoritePictures, Profiles profileId,List<PicturesAndUsers> picturesAndUsers,List<PicturesAndUsers> reportedPicturesAndUsers) {
		this.userNumber = userNumber;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRegisterDate = userRegisterDate;
		this.pictures = pictures;
		this.favoritePictures = favoritePictures;
		this.profileId = profileId;
		this.picturesAndUsers = picturesAndUsers;
		this.reportedPicturesAndUsers = reportedPicturesAndUsers;
	}


	
	
}
