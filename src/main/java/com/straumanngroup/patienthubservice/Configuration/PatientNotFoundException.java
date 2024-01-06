package com.straumanngroup.patienthubservice.Configuration;


public class PatientNotFoundException extends RuntimeException{
	public PatientNotFoundException(String message) {
        super(message);
    }
}
