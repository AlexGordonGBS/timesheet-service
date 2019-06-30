package com.alex.gordon.timesheet.service;

import com.alex.gordon.timesheet.repo.TimesheetRepo;
import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimesheetService.class);

    private TimesheetRepo timesheetRepo;

    public TimesheetService(TimesheetRepo timesheetRepo) {
        this.timesheetRepo = timesheetRepo;
    }

    public List<TimesheetEntity> getAll() {
        return timesheetRepo.findAll();

    }

    public TimesheetEntity addTimesheet(TimesheetEntity timesheetEntity) {
        return timesheetRepo.save(timesheetEntity);
    }

}
