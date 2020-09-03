package controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import picturesDTO.Pictures;
import profilesDTO.Profiles;
import repositories.PicturesRepository;
import repositories.ProfilesRepository;
import repositories.UsersRepository;
import usersDTO.Users;

@RestController
public class ProfilesController {
	  private final ProfilesRepository profilesRepository;
	  private final UsersRepository usersRepository;
	  private final PicturesRepository picturesRepository;
	  
	  ProfilesController(ProfilesRepository profilesRepository,UsersRepository usersRepository, PicturesRepository picturesRepository) {
	    this.profilesRepository = profilesRepository;
	    this.usersRepository = usersRepository;
	    this.picturesRepository = picturesRepository;
	  }

	  @PutMapping("/users/{userEmail}/profileImage")
	  public String updateProfileImage(@RequestParam("img") MultipartFile img, @PathVariable String userEmail) {
		  Users user = usersRepository.findByUserEmail(userEmail);
		  if (user != null) {
			  Profiles profile = profilesRepository.findByUserId(user);
			  if (profile == null) {
				  Profiles newProfilePic = Profiles.builder().userId(user).build();
				  profilesRepository.save(newProfilePic);
				  try {
					img.transferTo(new File("C:/OpenPose/FinPic/img/"+user.getUserEmail() + "_" + newProfilePic.getProfileNumber()+".jpg"));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			  }
			  else {
				  try {
					img.transferTo(new File("C:/OpenPose/FinPic/img/"+user.getUserEmail() + "_" + profile.getProfileNumber()+".jpg"));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			  }
			  return "redirect1:/somewhere.jsp";
			  }
		  else {
				return "redirect:/somewhere.jsp";
		  	}
	  }
	  
	  
  	  @PutMapping("/users/{userEmail}/profile")
	  public String updateProfile(@RequestParam(required = false, value = "introduction") String introduction,
			  @RequestParam(required = false, value = "snsLink1") String snsLink1,@RequestParam(required = false, value = "snsLink2") String snsLink2,
			  @RequestParam(required = false, value = "snsLink3") String snsLink3,@PathVariable String userEmail) {
		  	Users user = usersRepository.findByUserEmail(userEmail);
			if(user != null) {
				Profiles profile = profilesRepository.findByUserId(user);
				if (profile == null) {
					Profiles newProfile = Profiles.builder().introduction(introduction).snsLink1(snsLink1).snsLink2(snsLink2)
							.snsLink3(snsLink3).userId(user).build();
					profilesRepository.save(newProfile);
				}
				else {
					profile.setIntroduction(introduction);
					profile.setSnsLink1(snsLink1);
					profile.setSnsLink2(snsLink2);
					profile.setSnsLink3(snsLink3);
					profilesRepository.save(profile);
				}
				
				return "redirect1:/somewhere.jsp";
			}else {
				return "redirect:/somewhere.jsp";
			}
		}

}
