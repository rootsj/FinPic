package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import profilesDTO.Profiles;
import usersDTO.Users;
@Repository
public interface ProfilesRepository extends JpaRepository<Profiles, Long>{
	public Profiles findByUserId(Users user);

}
