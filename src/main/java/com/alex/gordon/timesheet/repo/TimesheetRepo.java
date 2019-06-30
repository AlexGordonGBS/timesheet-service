package com.alex.gordon.timesheet.repo;

import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimesheetRepo extends CrudRepository<TimesheetEntity, String> {

    @Override
    List<TimesheetEntity> findAll();

    List<TimesheetEntity> findByClient(String client);

}

