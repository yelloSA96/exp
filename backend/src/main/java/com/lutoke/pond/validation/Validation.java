package com.lutoke.pond.validation;

import com.lutoke.pond.model.employee.Employee;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class Validation {
	public static boolean IsValidEmployee(Employee employee) {
		if (employee == null) {
    		log.warn("Failed registration");
            return false;
        }
        // EMPTY FIELDS
        if (!IsValidInput(employee.getEmail())){
    		log.warn("Failed registration due to email");
    		return false;
        }else if (!IsValidInput(employee.getFirstName())) {
    		log.warn("Failed registration due to first name");
            return false;

        }else if (!IsValidInput(employee.getLastName())) {
    		log.warn("Failed registration due to last name");
            return false;

        }
		return true;
	}
	
	public static boolean IsValidInput(String string) {
		if(string==null || string.equals(""))
			return false;
		return true;
	}
}
