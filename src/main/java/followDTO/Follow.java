package followDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import usersDTO.Users;

@Getter
@Setter
@Entity
@Component
public class Follow {	
	@Id
	@GeneratedValue(generator = "Follow_seq_ger")
	@SequenceGenerator(name = "Follow_seq_ger", sequenceName = "Follow_seq", allocationSize = 1)
	long followNumber;
	
	@ManyToOne
	@JoinColumn(name="fromUser", nullable = false)
	private Users fromUser;
	
	//? 회원삭제 시 팔로우 테이블에서 데이터 삭제 (고민)
	@ManyToOne
	@JoinColumn(name="toUser", nullable = false)
	private Users toUser;
}
