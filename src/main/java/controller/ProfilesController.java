package controller;

import org.springframework.web.bind.annotation.RestController;

import repositories.ProfilesRepository;

@RestController
public class ProfilesController {
	  private final ProfilesRepository repository;
	  
	  ProfilesController(ProfilesRepository repository) {
	    this.repository = repository;
	  }
}
