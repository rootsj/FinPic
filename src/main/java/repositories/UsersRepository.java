package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import usersDTO.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	public Users findByUserNumber(long userNumber);
	public Users findByUserEmail(String userEmail);
	public Users findByUserEmailAndUserPassword(String userEmail, String userPassword);
	public Users findByUserName(String userName);
}
