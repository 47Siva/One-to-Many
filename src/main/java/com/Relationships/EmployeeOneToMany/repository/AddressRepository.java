package com.Relationships.EmployeeOneToMany.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Relationships.EmployeeOneToMany.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

}
