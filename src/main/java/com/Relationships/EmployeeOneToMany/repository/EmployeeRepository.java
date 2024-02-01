package com.Relationships.EmployeeOneToMany.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressDto;
import com.Relationships.EmployeeOneToMany.dto.EmployeeAddressList;
import com.Relationships.EmployeeOneToMany.entity.Employee;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, UUID>,CrudRepository<Employee, UUID>{

	//get by name
	@Query(value = "select e.name as EmployeeName ,"
			+ "e.job as EmployeeJob ,"
			+ "e.number as EmployeeNumber,"
			+ "e.salary as EmployeeSalary,"
			+ "a.state as AddressState,"
			+ "a.district as AdressDistrict,"
			+ "a.place as AddressPlace,"
			+ "a.plat_no as AddressPlatNo "
			+ "from employee e inner join address a on e.employee_id = a.emoloyee_id where e.name =:name",nativeQuery = true)
	List<EmployeeAddressList> findAllByName( String name);

	//get by id
	@Query(value = "select e.name as EmployeeName,"
			+ "e.job,e.number as EmployeeJob,"
			+ "e.salary as EmployeeSalary,a.district as AddressDistrict,"
			+ "a.state as AddressState,"
			+ "a.place as AddressPlace,a.plat_no as AddressPlatNo "
			+ "from employee  e inner join address  a  on e.employee_id = a.emoloyee_id ", nativeQuery =  true)
	List<EmployeeAddressList> findAllEmployee();
	
	//get any request param
	@Query("SELECT E.job,E.name,E.number,E.salary,"
			+ "A.district,A.place,A.state,A.platNo "
			+ "FROM Employee E INNER JOIN E.address A "
			+ "WHERE E.job=:d OR E.name =:d OR E.number=:d OR  E.salary=:d OR "
			+ "A.district=:d OR A.place=:d OR A.state=:d OR A.platNo=:d")
	List<Object> findByAny(@Param("d") Object searchKey);

	//get any path veriable
//	@Query("SELECT e.job,e.name,e.salary,"
//			+ "a.district,a.place,a.platNo "
//			+ "FROM Employee as e INNER JOIN e.address a "
//			+ "where e.job=:key OR e.name =:key OR e.number =:key OR e.salary =:key OR e.employee_id=:key or  "
//			+ "a.address_id=:key or a.district =:key OR a.place =:key OR a.state =:key OR a.platNo =:key")
//	List<Object> findAny( Object key);
	
	
//	@Query("SELECT e.employee_id, e.job, e.name, e.salary, a.address_id, a.district, a.place, a.platNo " +
//		       "FROM Employee e INNER JOIN e.address a " +
//		       "WHERE e.job LIKE %:key% OR " +
//		             "e.name LIKE %:key% OR " +
//		             "e.number LIKE %:key% OR " +
//		             "e.salary LIKE %:key% OR " +
//		             "e.employee_id LIKE %:key% OR " +
//		             "a.address_id LIKE %:key% OR " +
//		             "a.district LIKE %:key% OR " +
//		             "a.place LIKE %:key% OR " +
//		             "a.state LIKE %:key% OR " +
//		             "CAST(a.platNo AS string) LIKE %:key%")

	
	@Query(value = "select  * "
			+ "from employee e inner join address a on e.id = a.employee_id "
			+ "where a.plat_no =:key or e.job=:key or e.name=:key or e.number=:key or e.salary=:key or a.address_id=:key "
			+ " or  a.district=:key or a.place=:key or a.state=:key ",nativeQuery = true)
		List<Object> findBykey( @Param("key") Object searchkey);
	

//	@Query(value = "SELECT  e.name as empName ,e.job as empJob, e.number as empNumber, e.salary as empSalary ,"
//			+ "a.state as empState , a.district as empDistrict ,a.place as empPlace, a.plat_no as empPlatNo FROM employee e INNER JOIN address a "
//			+ "ON e.employee_id=a.employee_id where  e.name LIKE %:searchkey% OR e.job LIKE %:searchkey% OR e.number LIKE %:searchkey% OR e.salary LIKE %:searchkey% "
//			+ "OR a.state LIKE %:searchkey% OR a.district LIKE %:searchkey% OR a.place LIKE %:searchkey% OR  a.plat_no LIKE %:searchkey%",nativeQuery = true)
//	List<EmployeeAddressDto> findByDto( @Param("searchkey") String key);
//
	
	//jasper report in filter 
	@Query(value = "select  "
			+ "e.name as EmpName , "
			+ "e.job as EmpJob , "
			+ "e.number as EmpNumber, "
			+ "e.salary as EmpSalary, "
			+ "a.state as EmpState, "
			+ "a.district as EmpDistrict, "
			+ "a.place as EmpPlace, "
			+ "a.plat_no as EmpPlatNo "
			+ "from employee e inner join address a on e.id = a.employee_id where "
			+ " e.name =:key or e.job=:key or e.number =:key "
			+ "or e.salary =:key or a.address_id =:key or a.district =:key " 
			+ "or a.place =:key or a.plat_no =:key ",nativeQuery = true)
	
//	@Query(value = "select "
//			+ "e.id, e.job, e.number, e.salary,"
//			+ "a.address_id , a.state , a.district , a.place , a.plat_no "
//			+ "from Employee e inner join address a on e.id = a.employee_id where "
//			+ " e.name =:key or e.job=:key or e.number =:key "
//			+ "or e.salary =:key or a.address_id =:key or a.district =:key " 
//			+ "or a.place =:key or a.plat_no =:key ",nativeQuery = true)
	
	List<EmployeeAddressList> findByValue(Object key);
	

}
