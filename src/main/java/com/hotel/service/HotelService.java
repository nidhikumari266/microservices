package com.hotel.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hotel.entities.Hotel;

public interface HotelService {

	Hotel createHotel(Hotel hotel);

	List<Hotel> getList();

	ResponseEntity<Hotel> getById(String id);

}
