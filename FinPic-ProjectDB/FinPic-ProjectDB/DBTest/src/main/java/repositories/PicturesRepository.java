package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import picturesDTO.Pictures;
@Repository
public interface PicturesRepository extends JpaRepository<Pictures, Long>{

}
