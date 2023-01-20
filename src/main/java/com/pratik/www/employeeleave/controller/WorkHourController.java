package com.pratik.www.employeeleave.controller;

import com.pratik.www.employeeleave.db.entity.EmployeeLeaveEntity;
import com.pratik.www.employeeleave.model.WorkHourRequest;
import com.pratik.www.employeeleave.service.WorkHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employeeleave")
public class WorkHourController {

    @Autowired
    WorkHourService workHourService;

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Number getEmployeeLeave(@PathVariable("employeeId") String employeeId){
        return this.workHourService.getEmployeeLeaveCount(employeeId);
    }

    @GetMapping("/all")
    public List<EmployeeLeaveEntity> getAllEmployeesLeave(){
        return this.workHourService.getAllEmployeesLeave();
    }

//    @PostMapping("/find")
//    @ResponseStatus(HttpStatus.FOUND)
//    public Optional<EmployeeLeaveEntity> getEmployeeLeaveByEmployeeIdAndYearMonth(@RequestBody WorkHourRequest workHourRequest){
//        return this.workHourService.getEmployeeLeaveByEmployeeIdAndYearMonth(workHourRequest);
//    }
    @GetMapping("/find/{employeeId}/{yearMonth}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<EmployeeLeaveEntity> getEmployeeLeaveByEmployeeIdAndYearMonth(@PathVariable String employeeId, @PathVariable Number yearMonth){
        return this.workHourService.getEmployeeLeaveByEmployeeIdAndYearMonth(employeeId,yearMonth);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void setEmployeeLeave(@RequestBody EmployeeLeaveEntity employeeLeave){
        this.workHourService.setEmployeeLeave(employeeLeave);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeLeave(@PathVariable String id){
        this.workHourService.deleteEmployeeLeave(id);
    }
}
