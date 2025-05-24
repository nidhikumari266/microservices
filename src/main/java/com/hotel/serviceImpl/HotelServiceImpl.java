package com.hotel.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.entities.Hotel;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repositories.HotelRepository;
import com.hotel.service.HotelService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String id = UUID.randomUUID().toString();
		hotel.setId(id);
		return hotelRepository.save(hotel);
	}
	@Override
	public List<Hotel> getList() {
	    return hotelRepository.findAll();
	}

	@Override
	public ResponseEntity<Hotel> getById(String id) {
	    Hotel hotel = hotelRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
	    return ResponseEntity.ok(hotel);
	}

	
	
}
