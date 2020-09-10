package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import picturesDTO.Pictures;
import usersDTO.Users;
@Repository
public interface PicturesRepository extends JpaRepository<Pictures, Long>{
	public List<Pictures> findByUserId(Users userId);
}
