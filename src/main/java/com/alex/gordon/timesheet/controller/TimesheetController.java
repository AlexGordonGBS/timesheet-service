package com.alex.gordon.timesheet.controller;

import com.alex.gordon.timesheet.service.TimesheetService;
import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimesheetController {

    private TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping(value = "/timesheets", produces = {"application/json"})
    public ResponseEntity<List<TimesheetEntity>> getAll() {
        return new ResponseEntity<>(timesheetService.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/timesheets", produces = {"application/json"})
    public ResponseEntity<TimesheetEntity> addTimesheet(@RequestBody TimesheetEntity timesheetEntity) {
        return new ResponseEntity<>(timesheetService.addTimesheet(timesheetEntity), HttpStatus.OK);
    }

}
