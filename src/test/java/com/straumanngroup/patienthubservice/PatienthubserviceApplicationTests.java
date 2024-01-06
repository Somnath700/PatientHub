package com.straumanngroup.patienthubservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.straumanngroup.patienthubservice.Controller.PatientController;
import com.straumanngroup.patienthubservice.Entity.Patient;
import com.straumanngroup.patienthubservice.Service.PatientService;
@WebMvcTest(PatientController.class)
@SpringBootTest
class PatienthubserviceApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    void testGetPatient() throws Exception {
        // Arrange
        Long patientId = 1L;
        Mockito.when(patientService.getPatientById(patientId)).thenReturn(new Patient(patientId, "Aman", "Patel", 25));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/patients/{id}", patientId))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Aman Patel"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30));
    }

    @Test
    void testCreatePatient() throws Exception {
        // Arrange
        Patient newPatient = new Patient(null, "Arpan", "Rakshit", 25);
        Mockito.when(patientService.createPatient(any())).thenReturn(newPatient);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/patients/add-patient")
               .content("{ \"name\": \"Sayan\", \"age\": 25 }")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sayandip"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25));
        
    }

    @Test
    void testUpdatePatient() throws Exception {
        // Arrange
        Long patientId = 2L;
        Patient updatedPatient = new Patient(patientId, "Subha", "Chowdhury", 30);
        Mockito.when(patientService.updatePatient(eq(patientId), any())).thenReturn(updatedPatient);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/patients/{id}", patientId)
               .content("{ \"name\": \"Subha\", \"age\": 26 }")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Subhadip"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(28));
    }

    @Test
    void testDeletePatient() throws Exception {
        // Arrange
        Long patientId = 3L;
        Mockito.when(patientService.deletePatient(patientId)).thenReturn("Patient deleted successfully.");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/patients/{id}", patientId))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("Patient deleted successfully."));
    }

}
