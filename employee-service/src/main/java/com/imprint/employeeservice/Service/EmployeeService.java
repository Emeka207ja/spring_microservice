package com.imprint.employeeservice.Service;

import com.imprint.employeeservice.Dto.CreateEmployeeDto;
import com.imprint.employeeservice.Dto.CreateEmployeeResponse;
import com.imprint.employeeservice.Dto.EmployeeData;
import com.imprint.employeeservice.Dto.GetEmployeeResponse;

import java.util.List;

public interface EmployeeService {
    public CreateEmployeeResponse createEmployee(CreateEmployeeDto createEmployeeDto);
    public GetEmployeeResponse getEmployee(Long employeeId);
    public List<EmployeeData> getEmployeesInADept(String departmentCode);
}
