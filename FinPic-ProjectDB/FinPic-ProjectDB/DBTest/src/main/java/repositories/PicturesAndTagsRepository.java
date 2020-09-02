package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pictures.and.tagsDTO.PicturesAndTags;
@Repository
public interface PicturesAndTagsRepository extends JpaRepository<PicturesAndTags, Long>{
	
}
