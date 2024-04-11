package com.imprint.departmentservice.Service;

import com.imprint.departmentservice.Dto.CreateDptDto;
import com.imprint.departmentservice.Dto.CreateDptResponse;
import com.imprint.departmentservice.Dto.GetDepartmentDetailsResponse;
import com.imprint.departmentservice.Dto.GetDepartmentMsResponse;

public interface DepartmentService {
    CreateDptResponse createDepartment(CreateDptDto createDptDto);
    GetDepartmentMsResponse getDepartmentMs(String departmentCode);
    GetDepartmentDetailsResponse getDepartmentDetails(Long departmentId);
}
