package com.imprint.employeeservice.Controller;

import com.imprint.employeeservice.Dto.CreateEmployeeDto;
import com.imprint.employeeservice.Dto.CreateEmployeeResponse;
import com.imprint.employeeservice.Dto.EmployeeData;
import com.imprint.employeeservice.Dto.GetEmployeeResponse;
import com.imprint.employeeservice.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/create_employee")
    public ResponseEntity<CreateEmployeeResponse>createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto){
        return new ResponseEntity<>(employeeService.createEmployee(createEmployeeDto), HttpStatus.CREATED);
    }
    @GetMapping("/get_employee/{employeeId}")
    public ResponseEntity<GetEmployeeResponse>getEmployee(@PathVariable("employeeId") Long employeeId){
        return new ResponseEntity<>(employeeService.getEmployee(employeeId),HttpStatus.OK);
    }
    @GetMapping("/get_dept_employees/{deptCode}")
    public  ResponseEntity<List<EmployeeData>>getEmployeesInDept(@PathVariable("deptCode") String deptCode){
        return new ResponseEntity<>(employeeService.getEmployeesInADept(deptCode),HttpStatus.OK);
    }
}
//List<EmployeeData> getEmployeesInADept