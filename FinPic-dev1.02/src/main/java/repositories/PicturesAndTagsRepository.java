package repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pictures.and.tagsDTO.PicturesAndTags;
<<<<<<< HEAD:FinPic-dev1.02/src/main/java/repositories/PicturesAndTagsRepository.java
=======
import pictures.and.usersDTO.PicturesAndUsers;
>>>>>>> 6656d99c6bfacb1e0598db31d59c598e87bb6b2b:src/main/java/repositories/PicturesAndTagsRepository.java
import picturesDTO.Pictures;
import tagsDTO.Tags;
@Repository
public interface PicturesAndTagsRepository extends JpaRepository<PicturesAndTags, Long>{
	public List<PicturesAndTags> findByTagId(Tags tagId);
<<<<<<< HEAD:FinPic-dev1.02/src/main/java/repositories/PicturesAndTagsRepository.java
	public List<PicturesAndTags> findByPictureId(Pictures picId);
=======
	public List<PicturesAndTags> findByPictureId(Pictures picture);
>>>>>>> 6656d99c6bfacb1e0598db31d59c598e87bb6b2b:src/main/java/repositories/PicturesAndTagsRepository.java
}
