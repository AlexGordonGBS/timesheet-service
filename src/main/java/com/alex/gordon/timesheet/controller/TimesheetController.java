package com.alex.gordon.timesheet.controller;

import com.alex.gordon.timesheet.service.TimesheetService;
import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "/timesheets/client/{client}", produces = {"application/json"})
    public ResponseEntity<List<TimesheetEntity>> getByClient(@PathVariable String client) {
        return new ResponseEntity<>(timesheetService.getByClient(client), HttpStatus.OK);
    }

    @GetMapping(value = "/timesheets/{id}", produces = {"application/json"})
    public ResponseEntity<TimesheetEntity> getById(@PathVariable String id) {
        TimesheetEntity entity = timesheetService.getById(id).orElse(null);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/timesheets/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        timesheetService.deleteById(id);
        // DELETE operation is expected to be idempotent, therefore delete an already deleted item is operation successful!!!
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/timesheets", produces = {"application/json"})
    public ResponseEntity<TimesheetEntity> addTimesheet(@Valid @RequestBody TimesheetEntity timesheetEntity) {
        return new ResponseEntity<>(timesheetService.addTimesheet(timesheetEntity), HttpStatus.CREATED);
    }

    @PutMapping(value = "/timesheets/{id}", produces = {"application/json"})
    public ResponseEntity<TimesheetEntity> updateTimesheet(@PathVariable String id, @Valid @RequestBody TimesheetEntity timesheetEntity) {
        TimesheetEntity entity = timesheetService.updateTimesheet(id, timesheetEntity);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/timesheets/load", produces = {"application/json"})
    public ResponseEntity<List<TimesheetEntity>> loadData() {
        return new ResponseEntity<>(timesheetService.loadData(), HttpStatus.OK);
    }

}
