package gslab.service.employee.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gslab.service.employee.exception.ResourceNotFoundException;
import gslab.service.employee.model.Employee;
import gslab.service.employee.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
@Autowired
private EmployeeRepository employeeRepository;
	
	@Override
	public Employee getEmployeeById(long employeeId) throws ResourceNotFoundException {
		Optional<Employee> employeeDb = this.employeeRepository.findById(employeeId);
		
		if(employeeDb.isPresent()) {
			return employeeDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : "+ employeeId);
		}
		
	}

}
