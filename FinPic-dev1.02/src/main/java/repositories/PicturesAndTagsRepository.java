package repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pictures.and.tagsDTO.PicturesAndTags;
import picturesDTO.Pictures;
import tagsDTO.Tags;
@Repository
public interface PicturesAndTagsRepository extends JpaRepository<PicturesAndTags, Long>{
	public List<PicturesAndTags> findByTagId(Tags tagId);
	public List<PicturesAndTags> findByPictureId(Pictures picId);
}
