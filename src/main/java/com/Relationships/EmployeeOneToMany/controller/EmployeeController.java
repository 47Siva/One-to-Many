package com.Relationships.EmployeeOneToMany.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressList;
import com.Relationships.EmployeeOneToMany.dto.EmployeeDto;
import com.Relationships.EmployeeOneToMany.entity.Employee;
import com.Relationships.EmployeeOneToMany.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;

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
	public EmployeeDto getById(@PathVariable ("employee_id") UUID id) {
		return 	employeeService.getById(id);
	}
	
	//getAll
	@GetMapping("getall")
	public List<EmployeeDto> getAll() {
		return employeeService.getAll();
	}
	
	@GetMapping("/getAll")
	public List<Employee> getAll1() {
		return employeeService.getAll1();
	}
	
	//delete
	@DeleteMapping("delete/{employee_id}")
	public String deleteById(@PathVariable("employee_id") UUID id) {
		employeeService.delete(id);
		return "Data successfully Deleted";
	}
	
	//get By name
	@GetMapping("getByName")
	public List<EmployeeAddressList> getByName(@RequestParam (value = "name",required = false) String name){
		return employeeService.getByName(name);
	}
	
	@GetMapping("getByAny/{searchKey}")
	public List<Object> getByAny(@RequestParam ("searchKey") Object searchKey) {
		return employeeService.getByAny(searchKey);
	}
	
	@GetMapping("getAny/{searchkey}")
	public List<Object>getlist (@PathVariable ("searchkey")Object searchkey){
		return employeeService.getlist(searchkey);
	}
	
	//post man with filter
	@GetMapping("getByKey/{key}")
	public ResponseEntity<byte [] >getBykey(@PathVariable ("key") Object key) throws FileNotFoundException, JRException {
		byte [] reports = employeeService.getBykey(key);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		return new ResponseEntity<>(reports,header,HttpStatus.OK);
	}
	
	//chrome
	@GetMapping("getallEmp/{report}")
	public String getAllEmp(@PathVariable ("report") String report) throws FileNotFoundException, JRException {
		employeeService.getallEmp(report);
		return "Your report is reday"; 
	}
	
	// post man
	@GetMapping("/getallemp")
	public ResponseEntity<byte[]> getAll2() throws FileNotFoundException, JRException{
		byte [] report = employeeService.getAll2();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		return new ResponseEntity<>(report,header,HttpStatus.OK);
	}
	
	
 }
