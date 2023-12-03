package com.Relationships.EmployeeOneToMany.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Relationships.EmployeeOneToMany.dto.AddressDto;
import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressDto;
import com.Relationships.EmployeeOneToMany.dto.EmployeeDto;
import com.Relationships.EmployeeOneToMany.entity.Address;
import com.Relationships.EmployeeOneToMany.entity.Employee;
import com.Relationships.EmployeeOneToMany.repository.AddressRepository;
import com.Relationships.EmployeeOneToMany.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AddressRepository addressRepository;

	// post
	public Employee post(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		if (employee.getEmployee_id() != null) {
			Optional<Employee> employee1 = employeeRepository.findById(employeeDto.getEmployee_id());
			employee = employee1.get();
		} else {
			employee = new Employee();
		}
		employee.setEmployee_id(employeeDto.getEmployee_id());
		employee.setJob(employeeDto.getJob());
		employee.setName(employeeDto.getName());
		employee.setNumber(employeeDto.getNumber());

		List<Address> addressList = new ArrayList<Address>();
		for (AddressDto i : employeeDto.getAddress()) {
			Address address = new Address();
			if (i.getAddress_id() != null) {
				Optional<Address> address1 = addressRepository.findById(i.getAddress_id());
				address = address1.get();
			} else {
				address = new Address();
			}
			address.setAddress_id(i.getAddress_id());
			address.setDistrict(i.getDistrict());
			address.setPlace(i.getPlace());
			address.setPlatNo(i.getPlatNo());
			address.setState(i.getState());
			addressList.add(address);
		}
		employee.setAddress(addressList);
		return employeeRepository.save(employee);
	}

	// ListPost
	public List<Employee> listPost(List<EmployeeDto> employeeDto) {
		// TODO Auto-generated method stub
		List<Employee> employeeList = new ArrayList<>();
		
		for (EmployeeDto i : employeeDto) {
			Employee employeeObj = new Employee();
			if(employeeObj.getEmployee_id() != null) {
				Optional<Employee> employeeList1 = employeeRepository.findById(employeeObj.getEmployee_id());
				employeeObj = employeeList1.get(); 
			}
			else {
				employeeObj = new Employee();
			}
			employeeObj.setEmployee_id(i.getEmployee_id());
			employeeObj.setJob(i.getJob());
			employeeObj.setName(i.getName());
			employeeObj.setNumber(i.getNumber());

			List<Address> addresslist1 = new ArrayList<>();
			List<AddressDto> addressList = i.getAddress();
			for (AddressDto j : addressList) {
				Address addressObj = new Address();
				if(addressObj.getAddress_id() != null) {
					Optional<Address> addressList1 = addressRepository.findById(j.getAddress_id());
				    addressObj = addressList1.get();
				}
				else {
					
				}
				addressObj.setAddress_id(j.getAddress_id());
				addressObj.setDistrict(j.getDistrict());
				addressObj.setState(j.getState());
				addressObj.setPlace(j.getPlace());
				addressObj.setPlatNo(j.getPlatNo());
				addresslist1.add(addressObj);
			}
			employeeObj.setAddress(addresslist1);
			employeeList.add(employeeObj);
		}
		return employeeRepository.saveAll(employeeList);
	}

	// getById
	public EmployeeDto getById(UUID employee_id) {
		Employee employee = new Employee();
		Optional<Employee> employee1 = employeeRepository.findById(employee_id);
		employee = employee1.get();
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmployee_id(employee.getEmployee_id());
		employeeDto.setJob(employee.getJob());
		employeeDto.setName(employee.getName());
		employeeDto.setNumber(employee.getNumber());

		List<AddressDto> addressDtoList = new ArrayList<AddressDto>();
		List<Address> addressList = new ArrayList<>();
		addressList = employee.getAddress();
		for (Address i : addressList) {
			AddressDto addressDto = new AddressDto();
			addressDto.setAddress_id(i.getAddress_id());
			addressDto.setDistrict(i.getDistrict());
			addressDto.setState(i.getState());
			addressDto.setPlace(i.getPlace());
			addressDto.setPlatNo(i.getPlatNo());
			addressDtoList.add(addressDto);
		}
		employeeDto.setAddress(addressDtoList);
		return employeeDto;
	}

	// getAll
	public List<EmployeeDto> getAll() {
		// TODO Auto-generated method stub
		List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
		for (Employee i : employeeRepository.findAll()) {
			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setEmployee_id(i.getEmployee_id());
			employeeDto.setJob(i.getJob());
			employeeDto.setName(i.getName());
			employeeDto.setNumber(i.getNumber());
			List<AddressDto> addressDtoList = new ArrayList<AddressDto>();
			for (Address j : i.getAddress()) {
				AddressDto addressDto = new AddressDto();
				addressDto.setAddress_id(j.getAddress_id());
				addressDto.setDistrict(j.getDistrict());
				addressDto.setPlace(j.getPlace());
				addressDto.setPlatNo(j.getPlatNo());
				addressDto.setState(j.getState());
				addressDtoList.add(addressDto);
			}
			employeeDto.setAddress(addressDtoList);
			employeeDtoList.add(employeeDto);
		}
		return employeeDtoList;
	}

	public void delete(UUID employee_id) {
		// TODO Auto-generated method stub

		employeeRepository.deleteById(employee_id);

	}

	public List<EmployeeAddressDto> getByName(String name) {

		if(name == null) {			
			return employeeRepository.findAllEmployee();
		}else {
			return employeeRepository.findAllByName(name);
		}
		
	}

}
