package com.osidigital.ktm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osidigital.ktm.model.Bike;
import com.osidigital.ktm.service.IBikeService;

@RestController
@RequestMapping(value="/api/v1/")
public class BikeController {
	
	private IBikeService bikeService;	
	
	public BikeController(IBikeService bikeService) {
		this.bikeService = bikeService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
    public ResponseEntity<String> createBike(@RequestBody Bike bike) {
		String output = bikeService.createBike(bike);
    	return ResponseEntity.ok().body(output);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
    public ResponseEntity<String> updateBike(@RequestBody Bike bike, @PathVariable int id) {
		String output = bikeService.updateBike(bike, id);
    	return ResponseEntity.ok().body(output);	
	}
	
	@GetMapping("{id}")
    public ResponseEntity<Bike> getBike(@PathVariable int id) {
		Bike bike = bikeService.getBike(id);
    	return ResponseEntity.ok().body(bike);	
	}
	
	@GetMapping
    public ResponseEntity<List<Bike>> getAllBikes() {
		List<Bike> bikes = bikeService.getAllBikes();
    	return ResponseEntity.ok().body(bikes);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
    public ResponseEntity<String> deleteBike(@PathVariable int id) {
		String output = bikeService.deleteBike(id);
    	return ResponseEntity.ok().body(output);	
	}

}
