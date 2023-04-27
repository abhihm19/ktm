package com.osidigital.ktm.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bikes")
public class Bike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	private boolean status;
	
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private int version;
	
	
}
