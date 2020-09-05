package followersDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Component
public class Followers {	
	@Id
	@GeneratedValue(generator = "Followers_seq")
	@SequenceGenerator(name = "Followers_seq", sequenceName = "Followers_seq", allocationSize = 1)
	long followerNumber;
	public Followers(long followerNumber) {
		this.followerNumber = followerNumber;
	}
}
