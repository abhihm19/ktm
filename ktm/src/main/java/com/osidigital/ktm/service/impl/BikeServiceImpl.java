package com.osidigital.ktm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.osidigital.ktm.model.Bike;
import com.osidigital.ktm.repository.BikeRepository;
import com.osidigital.ktm.service.IBikeService;

@Service
public class BikeServiceImpl implements IBikeService{
	
	private BikeRepository bikeRepository;	

	public BikeServiceImpl(BikeRepository bikeRepository) {
		this.bikeRepository = bikeRepository;
	}

	@Override
	public Bike getBike(long id) {
		Bike bike = bikeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Bike not found"));
		if(bike.isStatus()) return bike;		
		else throw new RuntimeException("Bike not found");
	}

	@Override
	public List<Bike> getAllBikes() {		
		List<Bike> bikes = bikeRepository.findAll();
		List<Bike> filteredBikes = bikes.stream()
                .filter(bike -> bike.isStatus())
                .collect(Collectors.toList());
		
		return filteredBikes;
		
	}

	@Override
	public String createBike(Bike bike) {
		if(bike != null) {
			bikeRepository.save(bike);
		}else throw new RuntimeException("Bike not able to create");
		return "Bike successfully created";
	}

	@Override
	public String updateBike(Bike bike, long id) {
		Bike existingBike = bikeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Bike not found"));
		if(!existingBike.isStatus()) throw new RuntimeException("Bike not found");
		existingBike.setModelName(bike.getModelName());
		existingBike.setVariantCode(bike.getVariantCode());
		existingBike.setVariantName(bike.getVariantName());
		
		existingBike.setTorque(bike.getTorque());
		existingBike.setTransmission(bike.getTransmission());
		existingBike.setPower(bike.getPower());
		existingBike.setBore(bike.getBore());
		existingBike.setClutch(bike.getClutch());
		existingBike.setDisplacement(bike.getDisplacement());
		existingBike.setFuelConsumption(bike.getFuelConsumption());
		existingBike.setTankCapacity(bike.getTankCapacity());
		
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
