package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pictures.and.usersDTO.PicturesAndUsers;
import picturesDTO.Pictures;
import usersDTO.Users;

@Repository
public interface PicturesAndUsersRepository extends JpaRepository<PicturesAndUsers, Long>{
	public PicturesAndUsers findByLikedPictureIdAndLikedUserId(Pictures likedPicture, Users likedUser);
	public PicturesAndUsers findByReportedPictureIdAndReportedUserId(Pictures reportedPicture, Users reportedUser);
}
