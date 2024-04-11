package com.imprint.departmentservice.Service.Implementation;

import com.imprint.departmentservice.Dto.*;
import com.imprint.departmentservice.Entity.Department;
import com.imprint.departmentservice.Exception.AccessDeniedException;
import com.imprint.departmentservice.Exception.ResourceNotFoundException;
import com.imprint.departmentservice.Repository.DepartmentRepository;
import com.imprint.departmentservice.Service.DepartmentService;
import com.imprint.departmentservice.Service.EmployeeApiClient;
import com.imprint.departmentservice.Utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeApiClient employeeApiClient;
    @Override
    public CreateDptResponse createDepartment(CreateDptDto createDptDto) {
        if(departmentExist(createDptDto.getCode())){
            throw new AccessDeniedException("department already exist");
        }
        Department department = Department.builder()
                .code(createDptDto.getCode())
                .name(createDptDto.getName())
                .description(createDptDto.getDescription())
                .build();
        departmentRepository.save(department);
        return CreateDptResponse.builder()
                .code(ResponseCode.CREATED)
                .message("department created")
                .build();
    }

    @Override
    public GetDepartmentMsResponse getDepartmentMs(String departmentCode) {
        Department department = findByDeptCode(departmentCode);
        return GetDepartmentMsResponse.builder()
                .departmentId(department.getDepartmentId())
                .code(department.getCode())
                .description(department.getDescription())
                .name(department.getName())
                .build();
    }

    @Override
    public GetDepartmentDetailsResponse getDepartmentDetails(Long departmentId) {
        Department department = findDepartmentById(departmentId);
        List<EmployeeData> employeeData =employeeApiClient.getEmployeesInDept(department.getCode());
        return GetDepartmentDetailsResponse.builder()
                .code(department.getCode())
                .departmentId(department.getDepartmentId())
                .description(department.getDescription())
                .name(department.getName())
                .employeeData(employeeData)
                .build();
    }

    private Department findByDeptCode(String deptCode){
        return departmentRepository.findByCode(deptCode).orElseThrow(()->new ResourceNotFoundException("department does not exist"));
    }
    private Boolean departmentExist(String deptCode){
        return departmentRepository.existsByCode(deptCode);
    }
    private Department findDepartmentById(Long departmentId){
        return departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("department does not exist"));
    }
}
