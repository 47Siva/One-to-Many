package com.Relationships.EmployeeOneToMany.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class EmployeeDto {

    private UUID id;
	
	private String name;
	
	private String job;
	
	private long number;
	
	private double salary;
	
	private List<AddressDto> address;
}
