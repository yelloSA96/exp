package com.lutoke.pond.controller;

import com.lutoke.pond.model.employee.Consultant;

import com.lutoke.pond.repository.ConsultantRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
public class ConsultantController {

    @Autowired
    private ConsultantRepository cRepo;



    @GetMapping("/consultant")
    public Iterable<Consultant> getAll() {    	
        return cRepo.findAll();
    }
}
