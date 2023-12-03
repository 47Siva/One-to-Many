package com.Relationships.EmployeeOneToMany.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressDto;
import com.Relationships.EmployeeOneToMany.dto.EmployeeDto;
import com.Relationships.EmployeeOneToMany.service.EmployeeService;

@RestController
@RequestMapping("/api/EmployeeOneToMany")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	//post 
	@PostMapping ("post")
	public String post(@RequestBody EmployeeDto employeeDto) {
		employeeService.post(employeeDto);
		return "Data Successfully saved";
	}
	
	//listPost
	@PostMapping("listpost")
	public String listPost(@RequestBody List<EmployeeDto> employeeDto) {
		employeeService.listPost(employeeDto);
		return "Data Successfully saved";
	}
	
	//getById
	@GetMapping("getbyid/{employee_id}")
	public EmployeeDto getById(@PathVariable ("employee_id") UUID employee_id) {
		return 	employeeService.getById(employee_id);
	}
	
	//getAll
	@GetMapping("getall")
	public List<EmployeeDto> getAll() {
		return employeeService.getAll();
	}
	
	//delete
	@DeleteMapping("delete/{employee_id}")
	public String deleteById(@PathVariable("employee_id") UUID employee_id) {
		employeeService.delete(employee_id);
		return "Data successfully Deleted";
	}
	
	//get By name
	@GetMapping("getByName")
	public List<EmployeeAddressDto> getByName(@RequestParam (value = "name",required = false) String name){
		return employeeService.getByName(name);
	}
}
