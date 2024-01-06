package com.straumanngroup.patienthubservice.Service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.straumanngroup.patienthubservice.Configuration.PatientNotFoundException;
import com.straumanngroup.patienthubservice.Entity.Patient;
import com.straumanngroup.patienthubservice.Repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
    private PatientRepository patientRepository;

    @Cacheable("patients")
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));
    }

    public Patient createPatient(Patient patient) {
        // Additional logic, if needed
        return patientRepository.save(patient);
    }
    
    public Patient updatePatient(Long id, Patient patient) {
    	Patient updatePatient = new Patient();
    	updatePatient = getPatientById(id);
    	updatePatient.setAge(patient.getAge());
    	updatePatient.setFirstName(patient.getFirstName());
    	updatePatient.setLastName(patient.getLastName());
    	
    	
    	return patientRepository.save(updatePatient);
    }

    @CacheEvict(value = "patients", allEntries = true)
    public String deletePatient(Long id) {
        // Additional logic, if needed
        patientRepository.deleteById(id);
        return "The Patient has been deleted id:"+ id;
    }

}
