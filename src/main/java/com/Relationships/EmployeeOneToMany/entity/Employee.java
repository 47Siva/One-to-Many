package com.Relationships.EmployeeOneToMany.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "uuid-char")
	private UUID employee_id;
	
	private String name;
	
	private String job;
	
	private long number;
	
	private double salary;
	
	@OneToMany(targetEntity = Address.class,cascade = {CascadeType.ALL})
	@JoinColumn(name = "emoloyee_id",referencedColumnName = "employee_id")
	private List<Address> address;
	
}
