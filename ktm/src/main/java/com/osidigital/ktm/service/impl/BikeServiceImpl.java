package com.osidigital.ktm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.osidigital.ktm.dto.BikeDto;
import com.osidigital.ktm.model.Bike;
import com.osidigital.ktm.repository.BikeRepository;
import com.osidigital.ktm.service.IBikeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BikeServiceImpl implements IBikeService {

	private BikeRepository bikeRepository;
	private ModelMapper modelMapper;

	@Override
	public BikeDto getBike(long id) {
		Bike bike = bikeRepository.findById(id).orElseThrow(() -> new RuntimeException("Bike not found"));
//		BikeModelNameAndVcAndVn bike = bikeRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Bike not found"));
		if (!bike.isStatus()) throw new RuntimeException("Bike not found");
		
		return modelMapper.map(bike, BikeDto.class);
	
	}

	@Override
	public List<BikeDto> getAllBikes(Pageable pageable) {
		Page<Bike> bikes =  bikeRepository.findAllByStatusIsTrue(pageable);
		return bikes.stream()
				.map(bike -> modelMapper.map(bike, BikeDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public String createBike(BikeDto bikeDto) {
		if (bikeDto == null)
			throw new RuntimeException("Bike not able to create");
		String createdBy = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		Date currentDateTime = new Date();
		int version = 1;
		Bike bike = modelMapper.map(bikeDto, Bike.class);
		bike.setStatus(true);
		bike.setCreatedBy(createdBy);
		bike.setCreatedDate(currentDateTime);
		bike.setVersion(version);
		bikeRepository.save(bike);

		return "Bike successfully created";
	}

	@Override
	public String updateBike(BikeDto bikeDto, long id) {
		Bike existingBike = bikeRepository.findById(bikeDto.getId())
				.orElseThrow(() -> new RuntimeException("Bike not found"));
		if (!existingBike.isStatus())
			throw new RuntimeException("Bike not found");
		existingBike = modelMapper.map(bikeDto, Bike.class);

		String lastModifiedBy = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		Date lastModiefedDateTime = new Date();
		existingBike.setLastModifiedBy(lastModifiedBy);
		existingBike.setLastModifiedDate(lastModiefedDateTime);
		int version = existingBike.getVersion();
		existingBike.setVersion(++version);

		bikeRepository.save(existingBike);

		return "Bike updated successfully";
	}

	@Override
	public String deleteBike(long id) {
		Bike bike = bikeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Bike not found"));
		bike.setStatus(false);
		bikeRepository.save(bike);
		
		return "Bike deleted successfully";
	}

}
