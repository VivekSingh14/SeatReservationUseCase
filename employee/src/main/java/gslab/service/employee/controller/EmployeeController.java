package gslab.service.employee.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gslab.service.employee.exception.ResourceNotFoundException;
import gslab.service.employee.model.Employee;
import gslab.service.employee.model.Role;
import gslab.service.employee.repository.EmployeeRepository;
import gslab.service.employee.repository.RoleRepository;
import gslab.service.employee.services.SequenceGeneratorService;







@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
		    throws ResourceNotFoundException {
		        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		        return ResponseEntity.ok().body(employee);
		    }
	
	@PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        employee.setPassword(employee.getPassword());
        Role userRole = roleRepository.findByRole("ADMIN");
	    employee.setRoles(new HashSet<>(Arrays.asList(userRole)));
        System.out.println(employee.getId());
        
        return employeeRepository.save(employee);
    }
	
	@DeleteMapping("/employees")
	public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employeeRepository.delete(employee);
		Map < String, Boolean > response = new HashMap < > ();
		
        response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity <Employee> updateEmployee(@PathVariable(value = "id")Long employeeId,
	        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setPassword(employeeDetails.getPassword());
		employee.setOfficeLocation(employeeDetails.getOfficeLocation());
		employee.setPhoneNumber(employeeDetails.getPhoneNumber());
		employee.setProject(employeeDetails.getProject());
		
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
}






