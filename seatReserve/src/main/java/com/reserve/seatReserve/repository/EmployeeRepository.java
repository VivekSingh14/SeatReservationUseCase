package com.reserve.seatReserve.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reserve.seatReserve.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{

	
}
