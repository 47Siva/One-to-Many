package com.Relationships.EmployeeOneToMany.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class EmployeeAddressDto {
	
	private UUID id;

	private String name;

	private String job;

	private long number;

	private double salary;

	private UUID address_id;

	private String state;

	private String district;

	private String place;

	private double platNo;
	
	
}
