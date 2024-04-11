package com.imprint.employeeservice.Service;

import com.imprint.employeeservice.Dto.GetDepartmentMsResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")

//@Bulkhead(name="${spring.application.name}", fallbackMethod = "getEmployeeFallback")
public interface DepartmentApiClient {
    @GetMapping("api/v1/department/ms_get/{deptCode}")
    GetDepartmentMsResponse getDepartmentMs(@PathVariable("deptCode") String deptCode);
}
