package com.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.entities.Hotel;
import com.user.entities.User;

@FeignClient(name = "HOTELSERVICES")
public interface HotelService {

	@GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
	
//	@GetMapping("/user/{userId}")
//	User getUser(@PathVariable("userId") String userId);
//	
	
}
