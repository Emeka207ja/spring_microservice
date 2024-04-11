package com.imprint.employeeservice.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeData {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
}
