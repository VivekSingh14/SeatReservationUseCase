package gslab.service.employee.services;

import gslab.service.employee.exception.ResourceNotFoundException;
import gslab.service.employee.model.Employee;

public interface EmployeeService {
	Employee getEmployeeById(long employeeId) throws ResourceNotFoundException;

}
