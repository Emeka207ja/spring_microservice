package com.imprint.employeeservice.Dto;

import com.imprint.employeeservice.Util.ResponseCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeResponse {
    private String message;
    private ResponseCode code;
}
