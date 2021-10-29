package gslab.service.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import gslab.service.employee.model.Role;





public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}
