package com.imprint.employeeservice.Dto;

import lombok.Data;

@Data
public class CreateEmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
}
