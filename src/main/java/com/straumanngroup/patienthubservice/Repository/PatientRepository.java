package com.straumanngroup.patienthubservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.straumanngroup.patienthubservice.Entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
