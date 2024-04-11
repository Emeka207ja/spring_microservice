package com.imprint.employeeservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDepartmentMsResponse {
    private Long departmentId;
    private String name;
    private String description;
    private String code;
}
