package com.osidigital.ktm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osidigital.ktm.model.Bike;

public interface BikeRepository extends JpaRepository<Bike, Long> {	
	
}
