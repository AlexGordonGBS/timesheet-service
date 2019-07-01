package com.alex.gordon.timesheet.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    static ObjectMapper mapper = new ObjectMapper();

    public static String asJsonString(final TimesheetEntity entity) {
        try {
            return mapper.writeValueAsString(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TimesheetEntity asEntity(final String json) {
        try {
            return mapper.readValue(json, TimesheetEntity.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
