package com.pratik.www.employeeleave.controller;

import com.pratik.www.employeeleave.db.entity.EmployeeLeaveEntity;
import com.pratik.www.employeeleave.service.WorkHourProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/kafka/employeeleave")
public class KafkaWorkHourController {

    @Autowired
    private WorkHourProducer workHourProducer;

    @GetMapping("/find/{employeeId}/{yearMonth}")
    @ResponseStatus(HttpStatus.FOUND)
    public void getEmployeeLeaveByEmployeeIdAndYearMonth(@PathVariable String employeeId, @PathVariable Number yearMonth){
        workHourProducer.sendMessage(employeeId,yearMonth);
    }
}
