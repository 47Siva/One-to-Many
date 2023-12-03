package com.Relationships.EmployeeOneToMany.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressDto;
import com.Relationships.EmployeeOneToMany.entity.Employee;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

	@Query(value = "select e.name as EmployeeName ,"
			+ "e.job as EmployeeJob ,"
			+ "e.number as EmployeeNumber,"
			+ "e.salary as EmployeeSalary,"
			+ "a.state as AddressState,"
			+ "a.district as AdressDistrict,"
			+ "a.place as AddressPlace,"
			+ "a.plat_no as AddressPlatNo "
			+ "from employee e inner join address a on e.employee_id = a.emoloyee_id where e.name =:name",nativeQuery = true)
	List<EmployeeAddressDto> findAllByName( String name);

	@Query(value = "select e.name as EmployeeName,"
			+ "e.job,e.number as EmployeeJob,"
			+ "e.salary as EmployeeSalary,a.district as AddressDistrict,"
			+ "a.state as AddressState,"
			+ "a.place as AddressPlace,a.plat_no as AddressPlatNo "
			+ "from employee  e inner join address  a  on e.employee_id = a.emoloyee_id ", nativeQuery =  true)
	List<EmployeeAddressDto> findAllEmployee();

	

	

}
