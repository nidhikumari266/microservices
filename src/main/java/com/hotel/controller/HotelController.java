package com.hotel.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.repositories.HotelRepository;
import com.hotel.service.HotelService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotels(@RequestBody Hotel hotel){
		Hotel hotelList = hotelService.createHotel(hotel);
		return ResponseEntity.ok(hotelList);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getListOfHotel() {
	    List<Hotel> hotels = hotelService.getList();
	    return ResponseEntity.ok(hotels);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getUserById(@PathVariable String id){
		return hotelService.getById(id);
	}
	
}
