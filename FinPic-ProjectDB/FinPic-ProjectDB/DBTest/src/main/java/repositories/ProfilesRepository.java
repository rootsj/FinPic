package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import profilesDTO.Profiles;
@Repository
public interface ProfilesRepository extends JpaRepository<Profiles, Long>{

}
