package com.osidigital.ktm.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osidigital.ktm.dto.BikeDto;
import com.osidigital.ktm.service.IBikeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/v1/bike")
public class BikeController {
	
	private IBikeService bikeService;	
	
	public BikeController(IBikeService bikeService) {
		this.bikeService = bikeService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
    public ResponseEntity<String> createBike(@Valid @RequestBody BikeDto bikeDto) {
		String output = bikeService.createBike(bikeDto);
    	return ResponseEntity.ok().body(output);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
    public ResponseEntity<String> updateBike(@Valid @RequestBody BikeDto bikeDto, @PathVariable int id) {
		String output = bikeService.updateBike(bikeDto, id);
    	return ResponseEntity.ok().body(output);	
	}
	
	@GetMapping("{id}")
    public ResponseEntity<BikeDto> getBike(@PathVariable int id) {
    	return ResponseEntity.ok().body(bikeService.getBike(id));	
	}
	
	@GetMapping
    public ResponseEntity<List<BikeDto>> getAllBikes(@RequestParam(defaultValue = "1") int page) {
		int pageSize = 2; // set the page size internally
	    Pageable paging = PageRequest.of(page - 1, pageSize);
    	return ResponseEntity.ok().body(bikeService.getAllBikes(paging));	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
    public ResponseEntity<String> deleteBike(@PathVariable int id) {
		String output = bikeService.deleteBike(id);
    	return ResponseEntity.ok().body(output);	
	}

}
