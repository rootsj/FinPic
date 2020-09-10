package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tagsDTO.Tags;
@Repository
public interface TagsRepository extends JpaRepository<Tags, Long>{
	public Tags findByTagName(String tagName);
	public List<Tags> findByTagNameContaining(String tagName);
	public List<Tags> findByRegisterDateBetween(Date aDayAgo, Date currentDate);
}
