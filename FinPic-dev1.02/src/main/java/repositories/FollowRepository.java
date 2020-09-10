package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import followDTO.Follow;
import usersDTO.Users;


@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>{
	// unFolow
	@Transactional
	public void deleteByFromUserAndToUser(Users fromUser, Users toUser);
	
	// 팔로우 유무
	public boolean existsByFromUserAndToUser(Users fromUser, Users toUser);
	
	// 팔로우 리스트
	public List<Follow> findByFromUser(Users fromUser);
	
	// 팔로우 카운트
	public long countByFromUser(Users fromUser);
	
	// 팔로워 카운트
	public long countByToUser(Users toUser);

}
