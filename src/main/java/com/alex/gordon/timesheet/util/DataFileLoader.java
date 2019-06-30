package com.alex.gordon.timesheet.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataFileLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataFileLoader.class);

    // loads the config variable from the the properties file - application.yml.
    @Value("${dataFileName:unknownFile}")
    private String fileName;

    public List<TimesheetEntity> loadData() {
        List<TimesheetEntity> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(DataFileLoader.class.getResourceAsStream(fileName)));
        try {
            if (br.ready()) {
                String line;
                // skip the first line - headers...
                line = br.readLine();
                while ((line = br.readLine()) != null) {
                    // skip empty "commas" line
                    if (line.trim().equals(",,,,,,,,,,,,,,,,,,")) {
                        continue;
                    } else {
                        // parse a line
                        TimesheetEntity entity = convertLineToEntity(line);
                        data.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Can not read file " + fileName);
            throw new RuntimeException("Can not read file " + fileName);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // do nothing
            }
        }
        return data;
    }

    private TimesheetEntity convertLineToEntity(String line) {
        TimesheetEntity entity = new TimesheetEntity();
        String[] terms = line.split(",");
        entity.setDate(terms[0]);
        entity.setClient(terms[1]);
        entity.setProject(terms[2]);
        entity.setProjectCode(terms[3]);
        entity.setTask(terms[4]);
        entity.setHours(terms[5]);
        entity.setHoursRounded(terms[6]);
        entity.setBillable(terms[7]);
        entity.setInvoiced(terms[8]);
        entity.setApproved(terms[9]);
        entity.setFirstName(terms[10]);
        entity.setLastName(terms[11]);
        entity.setDepartment(terms[12]);
        entity.setEmployee(terms[13]);
        entity.setBillableRate(terms[14]);
        entity.setCostRate(terms[15]);
        entity.setCostAmount(terms[16]);
        entity.setCurrency(terms[17]);
        entity.setExternalReferenceUrl("");
        return entity;
    }

}
