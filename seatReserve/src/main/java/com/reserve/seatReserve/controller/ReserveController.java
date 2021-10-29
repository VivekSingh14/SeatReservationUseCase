package com.reserve.seatReserve.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.reserve.seatReserve.exception.ResourceNotFoundException;
import com.reserve.seatReserve.model.Desk;
import com.reserve.seatReserve.model.Employee;
import com.reserve.seatReserve.model.Reserve;
import com.reserve.seatReserve.repository.DeskRepository;
import com.reserve.seatReserve.repository.EmployeeRepository;
import com.reserve.seatReserve.repository.ReserveRepository;


@RestController
@RequestMapping("/gslab")
public class ReserveController {

	@Autowired
	ReserveRepository reserveRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@Autowired
	DeskRepository deskRepository;
	
	
	@PostMapping("/reserve/{id}")
    public Reserve reserveSeat(@PathVariable(value = "id") Long employeeId, @RequestBody ReserveValidation data)
        throws ResourceNotFoundException, ParseException{
		/*Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));*/
		//System.out.println(employee.getEmailId()+" "+ employee.getFirstName()+ " ");
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Employee> response = 
				restTemplate.getForEntity(
						"http://0.0.0.0:8081/api/v1/employee/{employeeId}",
						Employee.class, employeeId);
		Employee employee1 = response.getBody();
		
		ResponseEntity<Desk[]> response1 =
				  restTemplate.getForEntity(
				  "http://0.0.0.0:8082/api/v1/desks",
				  Desk[].class);
				Desk[] desks = response1.getBody();
		
		Reserve reserve = new Reserve(data.getStartDate() , data.getEndDate(), employee1, desks[0]);
		return reserveRepository.save(reserve);
	}
	
	@GetMapping("/employee/{username}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "username") String username) {
		List<Employee> employee = employeeRepository.findAll();
		Employee emp = null;
		for(int i=0;i<employee.size();i++) {
			if(username.equals(employee.get(i).getEmailId())) {
				 emp = employee.get(i);
				 System.out.println("Employee email Id: "+emp.getEmailId());
			}
		}
		
		return ResponseEntity.ok().body(emp);
	}
	
}
