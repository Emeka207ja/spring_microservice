package com.imprint.departmentservice.Dto;

import com.imprint.departmentservice.Utils.ResponseCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDptResponse {
    private String message;
    private ResponseCode code;
}
