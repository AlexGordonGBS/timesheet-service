package com.alex.gordon.timesheet.service;

import com.alex.gordon.timesheet.repo.TimesheetRepo;
import com.alex.gordon.timesheet.util.DataFileLoader;
import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimesheetService.class);
    private TimesheetRepo timesheetRepo;
    private DataFileLoader dataLoader;

    public TimesheetService(TimesheetRepo timesheetRepo, DataFileLoader dataLoader) {
        this.timesheetRepo = timesheetRepo;
        this.dataLoader = dataLoader;
    }

    public List<TimesheetEntity> getAll() {
        return timesheetRepo.findAll();
    }

    public List<TimesheetEntity> getByClient(String client) {
        return timesheetRepo.findByClient(client);
    }

    public Optional<TimesheetEntity> getById(String id) {
        return timesheetRepo.findById(id);
    }

    public void deleteById(String id) {
        timesheetRepo.deleteById(id);
        return;
    }

    public TimesheetEntity addTimesheet(TimesheetEntity timesheetEntity) {
        return timesheetRepo.save(timesheetEntity);
    }

    public TimesheetEntity updateTimesheet(String id, TimesheetEntity timesheetEntity) {
        TimesheetEntity originalTimesheet = timesheetRepo.findById(id).orElse(null);
        if (originalTimesheet == null) {
            return null;
        } else {
            timesheetEntity.setId(id);
            return timesheetRepo.save(timesheetEntity);
        }
    }

    public List<TimesheetEntity> loadData() {
        // load data here from the CSV file
        List<TimesheetEntity> data = dataLoader.loadData();
        // save all data
        timesheetRepo.saveAll(data);
        // return all data
        return timesheetRepo.findAll();
    }

}
