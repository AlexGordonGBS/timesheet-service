package com.alex.gordon.timesheet.controller;

import com.alex.gordon.timesheet.service.TimesheetService;
import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p> Controller class.</p>
 *
 * @author Alex Gordon
 */
@RestController
public class TimesheetController {

    private TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    /**
     * Returns ALL timesheets from the DB.
     *
     * @return list of timesheets
     */
    @GetMapping(value = "/timesheets", produces = {"application/json"})
    public ResponseEntity<List<TimesheetEntity>> getAll() {
        return new ResponseEntity<>(timesheetService.getAll(), HttpStatus.OK);
    }

    /**
     * Returns ALL timesheet for the client
     *
     * @param client
     * @return list of timesheets
     */
    @GetMapping(value = "/timesheets/client/{client}", produces = {"application/json"})
    public ResponseEntity<List<TimesheetEntity>> getByClient(@PathVariable String client) {
        return new ResponseEntity<>(timesheetService.getByClient(client), HttpStatus.OK);
    }

    /**
     * Returns one timesheet by ID
     *
     * @param id
     * @return timesheet
     */
    @GetMapping(value = "/timesheets/{id}", produces = {"application/json"})
    public ResponseEntity<TimesheetEntity> getById(@PathVariable String id) {
        TimesheetEntity entity = timesheetService.getById(id).orElse(null);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete one timesheet by ID.
     * If timesheet is NOT found then completes successfully.
     *
     * @param id
     * @return void
     */
    @DeleteMapping(value = "/timesheets/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        timesheetService.deleteById(id);
        // DELETE operation is expected to be idempotent, therefore delete an already deleted item is operation successful!!!
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Create one new timesheet and adds it to the database.
     *
     * @param timesheetEntity
     * @return a copy of the newly created timesheet with the generated ID and all other fields.
     */
    @PostMapping(value = "/timesheets", produces = {"application/json"})
    public ResponseEntity<TimesheetEntity> addTimesheet(@Valid @RequestBody TimesheetEntity timesheetEntity) {
        return new ResponseEntity<>(timesheetService.addTimesheet(timesheetEntity), HttpStatus.CREATED);
    }

    /**
     * Updates an existing timesheet.
     * All the fields are required.
     *
     * @param id              - mandatory
     * @param timesheetEntity
     * @return updated timesheet
     */
    @PutMapping(value = "/timesheets/{id}", produces = {"application/json"})
    public ResponseEntity<TimesheetEntity> updateTimesheet(@PathVariable String id, @Valid @RequestBody TimesheetEntity timesheetEntity) {
        TimesheetEntity entity = timesheetService.updateTimesheet(id, timesheetEntity);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Utility method - loads ALL timesheets from the provided CSV file to the database.
     *
     * @return
     */
    @PostMapping(value = "/timesheets/load", produces = {"application/json"})
    public ResponseEntity<List<TimesheetEntity>> loadData() {
        return new ResponseEntity<>(timesheetService.loadData(), HttpStatus.OK);
    }

}
