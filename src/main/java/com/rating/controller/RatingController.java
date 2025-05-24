package com.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rating.entities.Rating;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
	Rating ratingList =	ratingService.createRating(rating);
	return ResponseEntity.ok(rating);	
	}

	@GetMapping
	public List<Rating> getAllRating(){
		return ratingService.getAllRating();
	}
	
	@GetMapping("/hotel/{hotelId}")
	public List<Rating> getRatingsByHotelId(@PathVariable String hotelId) {
	    return ratingService.getRatingByHotelId(hotelId);
	}

	@GetMapping("/user/{userId}")
	public List<Rating> getRatingsByUserId(@PathVariable String userId) {
	    return ratingService.getRatingByUserId(userId);
	}

}
