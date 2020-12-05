package com.lutoke.pond.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.lutoke.pond.model.employee.*;
import com.lutoke.pond.repository.EmployeeRepository;
import com.lutoke.pond.validation.Validation;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api")
@Log4j2
public class RegisterEmployeeController {
	
    @Autowired
    EmployeeRepository employeeRepository;
    
    private final PasswordEncoder passwordEncoder;
    @Autowired
	public RegisterEmployeeController(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
    
	@PutMapping("/register/Consultant")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Secured("ROLE_ADMIN")
	public Consultant registerConsultant(@RequestBody Consultant consultant) {
		log.trace("Admin attempted to register a consultant");
        if(!Validation.IsValidEmployee(consultant))
        	return null;
		if (consultant == null) {
    		//logger.warn("Failed registration");
            return null;
        }
        // DUPLICATES
        else if (employeeRepository.findByEmail(consultant.getEmail())!=null) {
    		//logger.warn("Failed registration as username is already in use");
            return null;
        } else {
        	if(consultant.getPassword()==null)
        		consultant.setPassword(passwordEncoder.encode(consultant.getFirstName()));
            Consultant _consultant = new Consultant(consultant.getEmail(), consultant.getPassword(), consultant.getFirstName(),consultant.getLastName());
            log.info("New employee was made for user "+ _consultant.getEmail());
            employeeRepository.save(_consultant);
            return _consultant;
        }

    }
	
	@PutMapping("/register/Manager")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Manager registerManager(@RequestBody Manager manager) {
		log.trace("Admin attempted to register a manager");
        if(!Validation.IsValidEmployee(manager))
        	return null;
		if (manager == null) {
    		//logger.warn("Failed registration");
            return null;
        }

        // DUPLICATES
        else if (employeeRepository.findByEmail(manager.getEmail())!=null) {
    		//logger.warn("Failed registration as username is already in use");
            return null;
        } else {
        	if(manager.getPassword()==null)
        		manager.setPassword(passwordEncoder.encode(manager.getFirstName()));
        	Manager _manager = new Manager(manager.getEmail(), manager.getPassword(), manager.getFirstName(),manager.getLastName());
            log.info("New employee was made for user "+ _manager.getEmail());
            employeeRepository.save(_manager);
            return _manager;
        }

    }

	@PutMapping("/register/Admin")
	@RolesAllowed("ROLE_ADMIN")
	public Admin registerAdmin(@RequestBody Admin admin) {
		log.warn("Admin attempted to register an admin");
        if(!Validation.IsValidEmployee(admin)) {
        	log.warn("Admin attempted to register invalid admin");
        	return null;
        }
		if (admin == null) {
    		log.warn("Failed registration");
            return null;
        }

        // DUPLICATES
        else if (employeeRepository.findByEmail(admin.getEmail())!=null) {
    		log.warn("Failed registration: email is already in use");
            return null;
        } else {
        	if(admin.getPassword()==null)
        		admin.setPassword(passwordEncoder.encode(admin.getFirstName()));
            Admin _admin = new Admin(admin.getEmail(), admin.getPassword(), admin.getFirstName(),admin.getLastName());
            log.info("New admin was made for user "+ _admin.getEmail());
            employeeRepository.save(_admin);
            return _admin;
        }

    }


}
