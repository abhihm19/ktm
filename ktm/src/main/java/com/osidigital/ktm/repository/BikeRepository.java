package com.osidigital.ktm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.osidigital.ktm.model.Bike;

public interface BikeRepository extends JpaRepository<Bike, Long> {	
	 Page<Bike> findAllByStatusIsTrue(Pageable pageable);
//	 Optional<BikeModelNameAndVcAndVn> findById(long id);
}
