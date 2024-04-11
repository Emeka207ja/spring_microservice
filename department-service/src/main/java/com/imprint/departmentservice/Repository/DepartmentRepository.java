package com.imprint.departmentservice.Repository;

import com.imprint.departmentservice.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByCode(String code);
    Boolean existsByCode(String code);
}
