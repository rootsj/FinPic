package controller;

import org.springframework.web.bind.annotation.RestController;

import repositories.FollowersRepository;

@RestController
public class FollowersController {
	  private final FollowersRepository repository;
	  
	  FollowersController(FollowersRepository repository) {
	    this.repository = repository;
	  }
}
