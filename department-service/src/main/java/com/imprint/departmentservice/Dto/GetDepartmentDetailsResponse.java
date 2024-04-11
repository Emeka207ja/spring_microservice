package com.imprint.departmentservice.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetDepartmentDetailsResponse {
    private Long departmentId;
    private String name;
    private String description;
    private String code;
    private List<EmployeeData> employeeData;
}
