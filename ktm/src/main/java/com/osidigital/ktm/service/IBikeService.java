package com.osidigital.ktm.service;

import java.util.List;

import com.osidigital.ktm.model.Bike;

public interface IBikeService {

	Bike getBike(long id);
	List<Bike> getAllBikes();
	String createBike(Bike bike);
	String updateBike(Bike bike, long id);
	String deleteBike(long id);
}
