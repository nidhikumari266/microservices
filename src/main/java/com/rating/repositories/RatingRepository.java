package com.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String>{
	
	List<Rating> findByHotelId(String hotelId);

	List<Rating> findByUserId(String userId);
}
