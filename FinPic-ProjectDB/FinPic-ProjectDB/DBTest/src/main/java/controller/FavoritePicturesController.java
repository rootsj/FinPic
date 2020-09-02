package controller;

import org.springframework.web.bind.annotation.RestController;

import repositories.FavoritePicturesRepository;


@RestController
public class FavoritePicturesController {
	  private final FavoritePicturesRepository repository;
	  
	  FavoritePicturesController(FavoritePicturesRepository repository) {
	    this.repository = repository;
	  }
	  
}
