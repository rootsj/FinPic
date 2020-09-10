package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import followersDTO.Followers;
@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long>{

}
