package com.alex.gordon.timesheet.controller;

import com.alex.gordon.timesheet.TimesheetCrudApplication;
import com.alex.gordon.timesheet.repo.TimesheetRepo;
import com.alex.gordon.timesheet.util.DataFileLoader;
import com.alex.gordon.timesheet.util.JsonConverter;
import com.alex.gordon.timesheet.util.TimesheetEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TimesheetCrudApplication.class)
@WebAppConfiguration
public class TimesheetControllerTest {

    @MockBean
    TimesheetRepo repo;
    @Autowired
    private WebApplicationContext webContext;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void addTimesheet_positive() throws Exception {
        //Given
        TimesheetEntity entityExpected = getTestEntity();
        entityExpected.setId("testEntityID");
        //When
        doReturn(entityExpected).when(repo).save(entityExpected);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/timesheets")
                .content(JsonConverter.asJsonString(entityExpected))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //Then
        verify(repo, times(1)).save(entityExpected);
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        MvcResult mvcResult = resultActions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        TimesheetEntity entityActual = JsonConverter.asEntity(content);
        assertEquals(entityExpected, entityActual);
    }

    @Test
    public void addTimesheet_negative() throws Exception {
        //Given
        TimesheetEntity entityExpected = getTestEntity();
        entityExpected.setId("testEntityID");
        //missing client field"
        entityExpected.setClient("");
        //When
        doReturn(entityExpected).when(repo).save(entityExpected);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/timesheets")
                .content(JsonConverter.asJsonString(entityExpected))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //Then
        verify(repo, times(0)).save(entityExpected);
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private TimesheetEntity getTestEntity() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(DataFileLoader.class.getResourceAsStream("/TestEntity.json")));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String json = sb.toString();
        return JsonConverter.asEntity(json);
    }

}
