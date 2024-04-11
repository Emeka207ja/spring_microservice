package com.imprint.employeeservice.Service.Implementation;

import com.imprint.employeeservice.Dto.*;
import com.imprint.employeeservice.Entity.Employee;
import com.imprint.employeeservice.Exception.AccessDeniedException;
import com.imprint.employeeservice.Exception.ResourceNotFoundException;
import com.imprint.employeeservice.Repository.EmployeeRepository;
import com.imprint.employeeservice.Service.DepartmentApiClient;
import com.imprint.employeeservice.Service.EmployeeService;
import com.imprint.employeeservice.Util.ResponseCode;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentApiClient apiClient;
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public CreateEmployeeResponse createEmployee(CreateEmployeeDto createEmployeeDto) {
        if(EmployeeExist(createEmployeeDto.getEmail())){
            throw new AccessDeniedException("employee already exist");
        }
        Employee employee = Employee.builder()
                .departmentCode(createEmployeeDto.getDepartmentCode())
                .email(createEmployeeDto.getEmail())
                .firstName(createEmployeeDto.getFirstName())
                .lastName(createEmployeeDto.getLastName())
                .build();
        employeeRepository.save(employee);
        return CreateEmployeeResponse.builder()
                .code(ResponseCode.CREATED)
                .message("employee created")
                .build();
    }

    @Override
    @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getEmployeeFallback")
    public GetEmployeeResponse getEmployee(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        GetDepartmentMsResponse getDepartmentMsResponse = apiClient.getDepartmentMs(employee.getDepartmentCode());
        return GetEmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .department(getDepartmentMsResponse)
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
    }
    public GetEmployeeResponse getEmployeeFallback(Long employeeId,Throwable throwable) {
        Employee employee = findEmployeeById(employeeId);
        GetDepartmentMsResponse getDepartmentMsResponse = GetDepartmentMsResponse.builder()
                .departmentId(2L)
                .name("default")
                .description("default")
                .code("default")
                .build();
        logger.error("error " + throwable.getMessage());
//        GetDepartmentMsResponse getDepartmentMsResponse = apiClient.getDepartmentMs(employee.getDepartmentCode());
        return GetEmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .department(getDepartmentMsResponse)
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
    }


    @Override
    public List<EmployeeData> getEmployeesInADept(String departmentCode) {

        List<Employee> employees = findEmployeesInADept(departmentCode);
        return employees.stream().map(item->EmployeeData.builder()
                .employeeId(item.getEmployeeId())
                .lastName(item.getLastName())
                .email(item.getEmail())
                .firstName(item.getFirstName())
                .build()).collect(Collectors.toList());
    }


    private Boolean EmployeeExist(String email){
        return employeeRepository.existsByEmail(email);
    }
    private Employee findEmployeeById(Long employeeId){
        return employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("employee does not exist"));
    }
    private List<Employee>findEmployeesInADept(String departmentCode){
        return employeeRepository.findByDepartmentCode(departmentCode);
    }
}
