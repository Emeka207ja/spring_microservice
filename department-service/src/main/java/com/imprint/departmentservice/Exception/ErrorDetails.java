package com.imprint.departmentservice.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDetails {
    private String message;
    private String timeStamp;
    private String details;
}
