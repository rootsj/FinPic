package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import favorite.picturesDTO.FavoritePictures;
import picturesDTO.Pictures;
import usersDTO.Users;

@Repository
public interface FavoritePicturesRepository extends JpaRepository<FavoritePictures, Long> {
	// 해당유저의 즐겨찾기 리스트
	public List<FavoritePictures> findByUserId(Users userId);

	// pictureId에 해당하는 FavoritePictures 객체
	public FavoritePictures findByPictureId(Pictures pictureId);
}
