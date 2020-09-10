package pictures.and.usersDTO;


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
import usersDTO.Users;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Component
public class PicturesAndUsers {
	@Id
	@GeneratedValue(generator = "PicturesAndUsers_seq")
	@SequenceGenerator(name = "PicturesAndUserss_seq", sequenceName = "PicturesAndUsers_seq", allocationSize = 1)
	long pictureanduserNumber;
		
	@ManyToOne
	@JoinColumn(name = "Liked_PICTURES_ID")
	Pictures likedPictureId;
	
	@ManyToOne
	@JoinColumn(name = "Liked_Users_ID")
	Users likedUserId;
	
	@ManyToOne
	@JoinColumn(name = "REPORTED_PICTURES_ID")
	Pictures reportedPictureId;
	
	@ManyToOne
	@JoinColumn(name = "REPORTED_Users_ID")
	Users reportedUserId;
	
	public PicturesAndUsers(long pictureanduserNumber,Pictures likedPictureId, Users likedUserId, Pictures reportedPictureId, Users reportedUserId) {
		this.pictureanduserNumber = pictureanduserNumber;
		this.likedPictureId = likedPictureId;
		this.likedUserId = likedUserId;
		this.reportedPictureId = reportedPictureId;
		this.reportedUserId = reportedUserId;
	}
	
	
}
