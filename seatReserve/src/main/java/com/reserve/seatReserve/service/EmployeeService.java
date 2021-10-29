package com.reserve.seatReserve.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reserve.seatReserve.model.Employee;
import com.reserve.seatReserve.repository.EmployeeRepository;

@Service
public class EmployeeService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Employee> foundEmployee = employeeRepository.findAll();
		Employee emp1 = null;
		for(int i=0; i<foundEmployee.size();i++) {
			if(foundEmployee.get(i).getEmailId().equals(username)) {
			   emp1 = foundEmployee.get(i);
			}else {
				return null;
			}
		}
		
		
		String name = emp1.getEmailId();
		String pass = emp1.getPassword();
		return new User(name, pass, new ArrayList<>());
	}
	
	

}
