package favorite.picturesDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class FavoritePictures {
	@Id
	@GeneratedValue(generator = "FavoritePictures_seq")
	@SequenceGenerator(name = "FavoritePictures_seq", sequenceName = "FavoritePictures_seq", allocationSize = 1)
	long favoritepictureNumber;
	
	@ManyToOne
	@JoinColumn(name = "USERS_ID")
	Users userId;
	
	@ManyToOne
	@JoinColumn(name = "PICTURES_ID")
	Pictures pictureId;

	public FavoritePictures(long favoritepictureNumber, Users userId, Pictures pictureId) {
		this.favoritepictureNumber = favoritepictureNumber;
		this.userId = userId;
		this.pictureId = pictureId;
	}

	
}
