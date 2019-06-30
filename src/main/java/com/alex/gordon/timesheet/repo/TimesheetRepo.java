package com.alex.gordon.timesheet.repo;

import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TimesheetRepo extends CrudRepository<TimesheetEntity, UUID> {

    @Override
    List<TimesheetEntity> findAll();

    List<TimesheetEntity> findByClient(String client);

}

