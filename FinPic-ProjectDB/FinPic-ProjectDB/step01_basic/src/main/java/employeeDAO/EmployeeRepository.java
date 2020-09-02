package employeeDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employeeDTO.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
