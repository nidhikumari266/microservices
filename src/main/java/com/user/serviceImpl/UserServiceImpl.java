package com.user.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.entities.User;
import com.user.exception.ResourceNotFoundException;
import com.user.external.services.HotelService;
import com.user.repositories.UserRepository;
import com.user.service.UserService;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public User createUser(User user) {
		String id = UUID.randomUUID().toString();
		user.setUserId(id);
		return userRepository.save(user);
	}

	@Override
	public ResponseEntity<List<User>> getUserList() {
	    List<User> users = userRepository.findAll();
	    User user = userRepository.findById(users.get(0).getUserId()) .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "));
		//http://localhost:9092/rating/user/f7fdb647-5140-4537-960c-a7714d44e317
		
		//ArrayList<Rating> forObject = restTemplate.getForObject("http://localhost:9092/rating/user/"+user.getUserId(), ArrayList.class);
	    ArrayList<Rating> forObject = restTemplate.getForObject("http://RATINGSERVICE/rating/user/"+user.getUserId(), ArrayList.class);

		System.out.println("Fetched user ID: " + user.getUserId());

		logger.info("{}" , forObject);
		user.setRatings(forObject);
	    return ResponseEntity.ok(users);
	}
	
	@Override
	public User findById(String id) {
	    User user = userRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

	    Rating[] ratingArray = new Rating[0];
	    try {
	        ratingArray = restTemplate.getForObject(
	            "http://RATINGSERVICE/rating/user/" + user.getUserId(),
	            Rating[].class
	        );
	    } catch (Exception e) {
	        logger.error("Failed to fetch ratings for user {}: {}", user.getUserId(), e.getMessage());
	    }

	    List<Rating> ratingList = Arrays.stream(ratingArray)
	        .map(rating -> {
	            try {
//	                Hotel hotel = restTemplate.getForObject(
//	                    "http://HOTELSERVICES/hotels/" + rating.getHotelId(),
//	                    Hotel.class));
	            	Hotel hotel = hotelService.getHotel(rating.getHotelId());
	                
	                rating.setHotel(hotel);
	            } catch (Exception e) {
	                logger.error("Failed to fetch hotel for rating {}: {}", rating.getRatingId(), e.getMessage());
	            }
	            return rating;
	        }).collect(Collectors.toList());

	    user.setRatings(ratingList);
	    return user;
	}


}
