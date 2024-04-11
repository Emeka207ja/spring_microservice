package com.imprint.departmentservice.Service;
import com.imprint.departmentservice.Dto.EmployeeData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeApiClient {
    @GetMapping("api/v1/employee/get_dept_employees/{deptCode}")
    public List<EmployeeData> getEmployeesInDept(@PathVariable("deptCode") String deptCode);
}
