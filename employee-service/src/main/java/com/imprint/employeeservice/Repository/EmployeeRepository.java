package com.imprint.employeeservice.Repository;

import com.imprint.employeeservice.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
   List<Employee>findByDepartmentCode(String code);
    Optional<Employee>findByEmail(String email);
    Boolean existsByEmail(String email);
}
