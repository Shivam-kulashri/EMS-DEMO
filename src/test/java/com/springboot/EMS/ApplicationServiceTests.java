package com.springboot.EMS;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.EMS.controller.ApplicationController;
import com.springboot.EMS.dto.ApplicantsDetailDto;
import com.springboot.EMS.dto.ResponseMessageDto;
import com.springboot.EMS.exception.ResourceNotFoundException;
import com.springboot.EMS.model.Application;
import com.springboot.EMS.service.ApplicationService;

@SpringBootTest
public class ApplicationServiceTests {
	@InjectMocks
    private ApplicationController applicationController;

    @Mock
    private ApplicationService applicationService;

    private MockMvc mockMvc;

    private Application application;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(applicationController).build();

        // Initialize the application object to use in the test cases
        application = new Application();
        application.setId(1);
        application.setApplicationStatus("Applied");
        application.setDateApplied(new Date());
        // Add more fields initialization if required
    }

    @Test
    void testInsertInBatch() throws Exception {
        List<Application> applications = Arrays.asList(application);
        
        // Mocking the service layer to return the applications
        when(applicationService.insertInBatch(applications)).thenReturn(applications);

        mockMvc.perform(post("/application/batch/add")
                .contentType("application/json")
                .content("[{\"id\":1, \"applicationStatus\":\"Applied\", \"dateApplied\":\"2024-12-08\"}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].applicationStatus").value("Applied"));

        // Verify that the service method was called exactly once
        verify(applicationService, times(1)).insertInBatch(applications);
    }

    @Test
    void testInsertApplication() throws Exception {
        // Mocking the service layer to return the application
        when(applicationService.insertApplication(application)).thenReturn(application);

        mockMvc.perform(post("/application/add")
                .contentType("application/json")
                .content("{\"id\":1, \"applicationStatus\":\"Applied\", \"dateApplied\":\"2024-12-08\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.applicationStatus").value("Applied"));

        // Verify that the service method was called exactly once
        verify(applicationService, times(1)).insertApplication(application);
    }

    @Test
    void testGetAllApplications() throws Exception {
        List<Application> applications = Arrays.asList(application);
        
        // Mocking the service layer to return a list of applications
        when(applicationService.getAllApplication()).thenReturn(applications);

        mockMvc.perform(get("/application/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].applicationStatus").value("Applied"));

        // Verify that the service method was called exactly once
        verify(applicationService, times(1)).getAllApplication();
    }

    @Test
    void testGetAllClearedApplications() throws Exception {
        List<ApplicantsDetailDto> clearedApplications = Arrays.asList(new ApplicantsDetailDto());
        
        // Mocking the service layer to return cleared applications
        when(applicationService.getClearedApplications()).thenReturn(clearedApplications);

        mockMvc.perform(get("/application/getAllCleared"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty());

        // Verify that the service method was called exactly once
        verify(applicationService, times(1)).getClearedApplications();
    }

    @Test
    void testGetAllOnboardedApplications() throws Exception {
        List<ApplicantsDetailDto> onboardedApplications = Arrays.asList(new ApplicantsDetailDto());
        
        // Mocking the service layer to return onboarded applications
        when(applicationService.getOnboardedApplications()).thenReturn(onboardedApplications);

        mockMvc.perform(get("/application/getAllOnboarded"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty());

        // Verify that the service method was called exactly once
        verify(applicationService, times(1)).getOnboardedApplications();
    }

    @Test
    void testUpdateApplication() throws Exception {
        Application updatedApplication = new Application();
        updatedApplication.setId(1);
        updatedApplication.setApplicationStatus("Cleared");
        
        // Mocking the service layer to return the updated application
        when(applicationService.validate(1)).thenReturn(application);
        when(applicationService.insertApplication(application)).thenReturn(updatedApplication);

        mockMvc.perform(put("/application/update/1")
                .contentType("application/json")
                .content("{\"applicationStatus\":\"Cleared\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.applicationStatus").value("Cleared"));

        // Verify that the service method was called exactly once
        verify(applicationService, times(1)).validate(1);
        verify(applicationService, times(1)).insertApplication(application);
    }

    @Test
    void testUpdateApplicationNotFound() throws Exception {
        ResponseMessageDto responseMessage = new ResponseMessageDto();
        responseMessage.setMsg("Resource not found");
        
        // Mocking the service layer to throw ResourceNotFoundException
        when(applicationService.validate(1)).thenThrow(new ResourceNotFoundException("Resource not found"));

        mockMvc.perform(put("/application/update/1")
                .contentType("application/json")
                .content("{\"applicationStatus\":\"Cleared\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.msg").value("Resource not found"));

        // Verify that the service method was called exactly once
        verify(applicationService, times(1)).validate(1);
    }
}
