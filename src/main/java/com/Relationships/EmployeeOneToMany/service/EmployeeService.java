package com.Relationships.EmployeeOneToMany.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.Relationships.EmployeeOneToMany.dto.AddressDto;
import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressDto;
import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressList;
import com.Relationships.EmployeeOneToMany.dto.EmployeeDto;
import com.Relationships.EmployeeOneToMany.entity.Address;
import com.Relationships.EmployeeOneToMany.entity.Employee;
import com.Relationships.EmployeeOneToMany.repository.AddressRepository;
import com.Relationships.EmployeeOneToMany.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AddressRepository addressRepository;

	// post
	public Employee post(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		if (employee.getId() != null) {
			Optional<Employee> employee1 = employeeRepository.findById(employeeDto.getId());
			employee = employee1.get();
		} else {
			employee = new Employee();
		}
		employee.setId(employeeDto.getId());
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
			if(employeeObj.getId() != null) {
				Optional<Employee> employeeList1 = employeeRepository.findById(employeeObj.getId());
				employeeObj = employeeList1.get(); 
			}
			else {
				employeeObj = new Employee();
			}
			employeeObj.setId(i.getId());
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
		employeeDto.setId(employee.getId());
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
			employeeDto.setId(i.getId());
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

	public void delete(UUID id) {
		// TODO Auto-generated method stub

		employeeRepository.deleteById(id);

	}

	public List<EmployeeAddressList> getByName(String name) {

		if(name == null) {			
			return employeeRepository.findAllEmployee();
		}else {
			return employeeRepository.findAllByName(name);
		}
		
	}

	public List<Object> getByAny(Object searchKey) {
		return employeeRepository.findByAny(searchKey);
	}

	public List<Object> getlist(Object searchkey) {
		return employeeRepository.findBykey(searchkey);
	}

	//post man with filter
	public byte[] getBykey(Object key) throws FileNotFoundException, JRException {
		List<EmployeeAddressList> employee = employeeRepository.findByValue(key);
		String filepath = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\EmployeeOneToMany\\src\\main\\resources\\Report\\Report.jrxml";
		
		//load and compile it
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		// maping jasper report and db
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employee);
		
		Map<String,Object> map = new HashMap<>();
		map.put("createdBy", "Employee");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map,dataSource);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	// chrome	

	public String getallEmp(String report) throws FileNotFoundException, JRException {
		
		List<Employee> employee = employeeRepository.findAll();
		String filepath = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\EmployeeOneToMany\\src\\main\\resources\\Report\\Report.jrxml";
		String Path = "C:\\Users\\VC\\Desktop\\newReport";
		
		//load and compile it
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employee);
		
		Map<String, Object> map = new HashMap<>();
		map.put("createdBy", "Employee");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,dataSource);
		
		if (report.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, Path+"employee.html");
		}
		
		if (report.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint,Path+"employee.pdf");
			
		}
		
		return "report generatedin in path : " + Path;
		
	}

	public List<Employee> getAll1() {
		
		return employeeRepository.findAll();
	}

	//post man
	public byte[] getAll2() throws FileNotFoundException, JRException {
//		List<Employee> employee = employeeRepository.findAll();
		
		List<EmployeeAddressDto> employeeAddressDtoList = new ArrayList<EmployeeAddressDto>();
		
		for (Employee i : employeeRepository.findAll()) {
			EmployeeAddressDto employeeAddressDto = new EmployeeAddressDto();
			employeeAddressDto.setId(i.getId());
			employeeAddressDto.setName(i.getName());
			employeeAddressDto.setJob(i.getJob());
			employeeAddressDto.setNumber(i.getNumber());
			employeeAddressDto.setSalary(i.getSalary());
			for(Address a : i.getAddress()) {
				employeeAddressDto.setAddress_id(a.getAddress_id());
				employeeAddressDto.setState(a.getState());
				employeeAddressDto.setDistrict(a.getDistrict());
				employeeAddressDto.setPlace(a.getPlace());
				employeeAddressDto.setPlatNo(a.getPlatNo());
			}
			employeeAddressDtoList.add(employeeAddressDto);
		}
		
		String filepath = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\EmployeeOneToMany\\src\\main\\resources\\Report\\Report.jrxml";
		
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeAddressDtoList);
		
		Map<String, Object> map = new HashMap<>();
		map.put("createdBy", "Employee");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,dataSource);
		
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	

}
