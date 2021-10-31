package com.reserve.seatReserve.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.reserve.seatReserve.exception.ResourceNotFoundException;
import com.reserve.seatReserve.model.AuthenticationRequest;
import com.reserve.seatReserve.model.AuthenticationResponse;
import com.reserve.seatReserve.model.Desk;
import com.reserve.seatReserve.model.Employee;
import com.reserve.seatReserve.model.Reserve;
import com.reserve.seatReserve.repository.EmployeeRepository;
import com.reserve.seatReserve.repository.ReserveRepository;
import com.reserve.seatReserve.service.EmployeeService;
import com.reserve.seatReserve.utils.JwtUtils;

@RestController
public class AuthController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	ReserveRepository reserveRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Value("${EMPLOYEE_URL:default_value}")
	private String employeeUrl;
	
	@Value("${DESK_URL:default_value}")
	private String deskUrl;
	
	@GetMapping("/test")
	public String firstPage() {
		return "Hello World"+ SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	
	@PostMapping("/reserve")
    public Reserve reserveSeat(@RequestBody ReserveValidation data)
        throws ResourceNotFoundException, ParseException{
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Desk[]> response1 =
				  restTemplate.getForEntity("http://0.0.0.0:8082/api/v1/desks",
				  Desk[].class);
				Desk[] desks = response1.getBody();
		
		List<Employee> emplist = employeeRepository.findAll();
		List<Reserve> reservelist = reserveRepository.findAll();
		ResponseEntity<Employee> response=null;
		for(int i=0; i< emplist.size();i++) {
		if(SecurityContextHolder.getContext().getAuthentication().getName().equals(emplist.get(i).getEmailId())) {
			
			//Long employeeId = emplist.get(i).getId();
			response = restTemplate.getForEntity("http://0.0.0.0:8081/api/v1/employee/{employeeId}",
					Employee.class, emplist.get(i).getId());
		}
		}
		
		Employee employee1 = response.getBody();
		//fetching allocated desks
		Desk[] tempDesks = new Desk[reservelist.size()];
        for(int i =0; i< reservelist.size();i++) {
            tempDesks[i] = reservelist.get(i).getDesk();
        }
        Reserve reserve = null;
        for(int i=0; i< desks.length;i++) {
        	if(tempDesks.length == 0 && desks[i].getOffice().equals(employee1.getOfficeLocation())) {
    				reserve = new Reserve(data.getStartDate() , data.getEndDate(), employee1, desks[i]);
        	}
        	for(int j=0;j<tempDesks.length;j++) {
        		if(desks[i] != tempDesks[j]) {
        			if(desks[i].getOffice().equals(employee1.getOfficeLocation())) {
        				reserve = new Reserve(data.getStartDate() , data.getEndDate(), employee1, desks[i]);
        			}
        		}
        	}
        }
		
		return reserveRepository.save(reserve);
	}
	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest){
		
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		
		
		System.out.println("environment variable after authentication");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during user authentication "+ username));
		}
		
		UserDetails loadedUser = employeeService.loadUserByUsername(username);
		
		String generatedToken = jwtUtils.generateToken(loadedUser);
		
		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));

	}
	
	
}




















