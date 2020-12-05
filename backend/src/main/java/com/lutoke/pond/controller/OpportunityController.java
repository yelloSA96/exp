package com.lutoke.pond.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lutoke.pond.repository.OpportunityRepository;
import com.lutoke.pond.model.opportunity.Opportunity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController {
    private final OpportunityRepository opportunityRepo;

    @Autowired
    public OpportunityController(OpportunityRepository opportunityRepo) {
        this.opportunityRepo = opportunityRepo;
    }

    @GetMapping
    public List<Opportunity> getOpportunity(@RequestParam(value = "id", required = false) Long id) {
        Iterable<Opportunity> opportunities = (id == null) ? opportunityRepo.findAll() :
                opportunityRepo.findAllById(Collections.singletonList(id));

        List<Opportunity> result = new ArrayList<>();
        for (Opportunity opportunity: opportunities) {
            if (opportunity.getVisible()) result.add(opportunity);
        }

        return result;
    }

    @DeleteMapping
    public void deleteOpportunityById(@RequestParam(value = "id") Long id) {
        Opportunity opportunity = opportunityRepo.findById(id).orElse(null);
        if (opportunity == null) return;
        log.info("[Entity] Opportunity {} to be removed.", id);
        opportunity.setVisible(false);

        opportunityRepo.save(opportunity);
    }
}
