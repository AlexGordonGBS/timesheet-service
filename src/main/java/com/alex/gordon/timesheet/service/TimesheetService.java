package com.alex.gordon.timesheet.service;

import com.alex.gordon.timesheet.repo.TimesheetRepo;
import com.alex.gordon.timesheet.util.DataFileLoader;
import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * <p> Service class.</p>
 *
 * @author Alex Gordon
 */
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
        String methodName = "getAll";
        LOGGER.info(String.format("Method=%s", methodName));
        List<TimesheetEntity> entities = timesheetRepo.findAll();
        LOGGER.info(String.format("Method=%s | Number of entities returned=%s", methodName, entities.size()));
        return entities;
    }

    public List<TimesheetEntity> getByClient(String client) {
        String methodName = "getByClient";
        LOGGER.info(String.format("Method=%s | client=%s", methodName, client));
        List<TimesheetEntity> entities = timesheetRepo.findByClient(client);
        LOGGER.info(String.format("Method=%s | client=%s | Number of entities returned=%s", methodName, client, entities.size()));
        return entities;
    }

    public Optional<TimesheetEntity> getById(String id) {
        String methodName = "getById";
        LOGGER.info(String.format("Method=%s | id=%s", methodName, id));
        Optional<TimesheetEntity> entity = timesheetRepo.findById(id);
        LOGGER.info(String.format("Method=%s | entity=%s", methodName, entity));
        return entity;
    }

    public void deleteById(String id) {
        String methodName = "deleteById";
        LOGGER.info(String.format("Method=%s | id=%s", methodName, id));
        timesheetRepo.deleteById(id);
        LOGGER.info(String.format("Method=%s | entity with id=%s has been deleted", methodName, id));
        return;
    }

    public TimesheetEntity addTimesheet(TimesheetEntity timesheetEntity) {
        String methodName = "addTimesheet";
        LOGGER.info(String.format("Method=%s | Entity=%s", methodName, timesheetEntity));
        String id = UUID.randomUUID().toString();
        timesheetEntity.setId(id);
        TimesheetEntity newEntity = timesheetRepo.save(timesheetEntity);
        LOGGER.info(String.format("Method=%s | New Entity ADDED=%s", methodName, newEntity));
        return newEntity;
    }

    public TimesheetEntity updateTimesheet(String id, TimesheetEntity timesheetEntity) {
        String methodName = "updateTimesheet";
        LOGGER.info(String.format("Method=%s | RequestEntity=%s", methodName, timesheetEntity));
        TimesheetEntity originalTimesheet = timesheetRepo.findById(id).orElse(null);
        LOGGER.info(String.format("Method=%s | OriginalEntity=%s", methodName, originalTimesheet));
        if (originalTimesheet == null) {
            return null;
        } else {
            timesheetEntity.setId(id);
            TimesheetEntity updatedTimesheet = timesheetRepo.save(timesheetEntity);
            LOGGER.info(String.format("Method=%s | UpdatedEntity=%s", methodName, updatedTimesheet));
            return updatedTimesheet;
        }
    }

    public List<TimesheetEntity> loadData() {
        String methodName = "loadData";
        LOGGER.info(String.format("Method=%s", methodName));
        // load data here from the CSV file
        List<TimesheetEntity> data = dataLoader.loadData();
        // save all data
        timesheetRepo.saveAll(data);
        // return all data
        List<TimesheetEntity> entities = timesheetRepo.findAll();
        LOGGER.info(String.format("Method=%s | Number of entities in the database=%s", methodName, entities.size()));
        return entities;
    }

    public void deleteAll() {
        String methodName = "deleteAll";
        LOGGER.info(String.format("Method=%s", methodName));
        timesheetRepo.deleteAll();
        LOGGER.info(String.format("Method=%s completed normally", methodName));
        return;
    }

}
