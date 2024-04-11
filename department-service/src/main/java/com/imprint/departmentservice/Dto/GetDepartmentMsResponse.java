package com.imprint.departmentservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDepartmentMsResponse {
    private Long departmentId;
    private String name;
    private String description;
    private String code;
}
