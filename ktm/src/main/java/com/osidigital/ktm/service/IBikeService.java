package com.osidigital.ktm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.osidigital.ktm.dto.BikeDto;

public interface IBikeService {

	BikeDto getBike(long id);
	List<BikeDto> getAllBikes(Pageable pageable);
	String createBike(BikeDto bikeDto);
	String updateBike(BikeDto bikeDto, long id);
	String deleteBike(long id);
}
