package com.imprint.departmentservice.Controller;

import com.imprint.departmentservice.Dto.CreateDptDto;
import com.imprint.departmentservice.Dto.CreateDptResponse;
import com.imprint.departmentservice.Dto.GetDepartmentDetailsResponse;
import com.imprint.departmentservice.Dto.GetDepartmentMsResponse;
import com.imprint.departmentservice.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/create_dept")
    public ResponseEntity<CreateDptResponse>createDepartment(@RequestBody CreateDptDto createDptDto){
        return new ResponseEntity<>(departmentService.createDepartment(createDptDto), HttpStatus.CREATED);
    }
    @GetMapping("/get_department/{deptId}")
    public ResponseEntity<GetDepartmentDetailsResponse>getDepartment(@PathVariable("deptId") Long deptId){
        return new ResponseEntity<>(departmentService.getDepartmentDetails(deptId),HttpStatus.OK);
    }
    @GetMapping("/ms_get/{deptCode}")
    public ResponseEntity<GetDepartmentMsResponse>getDepartmentMs(@PathVariable("deptCode") String deptCode){
        return new ResponseEntity<>(departmentService.getDepartmentMs(deptCode),HttpStatus.OK);
    }
}
//GetDepartmentDetailsResponse getDepartmentDetails
