package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import favorite.picturesDTO.FavoritePictures;

@Repository
public interface FavoritePicturesRepository extends JpaRepository<FavoritePictures, Long>{

}
