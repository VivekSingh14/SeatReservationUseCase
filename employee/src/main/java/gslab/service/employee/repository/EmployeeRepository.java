package gslab.service.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import gslab.service.employee.model.Employee;



public interface EmployeeRepository extends MongoRepository<Employee, Long>{
          
}
