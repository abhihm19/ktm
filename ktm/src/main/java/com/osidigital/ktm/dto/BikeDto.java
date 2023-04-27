package com.osidigital.ktm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BikeDto {
	
	private long id;
	private String modelName;
	private String variantCode;
	private String variantName;
	
	private int torque;
	private int transmission;
	private int power;
	private int bore;
	private String clutch;
	private int displacement;
	private String fuelConsumption;	
	private double tankCapacity;
}
