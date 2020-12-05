package com.lutoke.pond.controller;

import com.lutoke.pond.model.employee.Consultant;
import com.lutoke.pond.model.employee.Skill;
import com.lutoke.pond.model.opportunity.Opportunity;
import com.lutoke.pond.model.schedule.*;
import com.lutoke.pond.repository.*;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//never import java.util.* - there are a lot of utility functions and we don't need them all
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Log4j2
@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleController {

    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final ConsultantRepository consultantRepo;
    private final ScheduleEntryRepository scheduleEntryRepo;
    private final SkillRepository skillRepo;
    private final OpportunityRepository opportunityRepo;

    @Autowired
    public ScheduleController(
    		ConsultantRepository consultantRepo,
            OpportunityRepository opportunityRepo,
            ScheduleEntryRepository scheduleEntryRepo,
            SkillRepository skillRepo
    ) {
        this.consultantRepo = consultantRepo;
        this.opportunityRepo = opportunityRepo;
        this.scheduleEntryRepo = scheduleEntryRepo;
        this.skillRepo = skillRepo;
    }

    private List<ScheduleEntry> getAll() {
        List<ScheduleEntry> entries = new ArrayList<>();
        Iterator<ScheduleEntry> iterator = scheduleEntryRepo.findAll().iterator();
        while (iterator.hasNext()) {
            entries.add(iterator.next());
        }
        return entries;
    }

    private List<ScheduleEntry> getByConsultantId(Long id) {
        List<ScheduleEntry> entries = new ArrayList<>();
        Iterator<ScheduleEntry> iterator = scheduleEntryRepo.findByConsultant_EmployeeID(id).iterator();
        while (iterator.hasNext()) {
            entries.add(iterator.next());
        }
        return entries;
    }

    // usage e.g.: /api/schedule?consultant=1&type=interview
    @GetMapping
    public List<ScheduleEntry> getScheduleEntryByConsultantIdAndType(
            @RequestParam(value = "consultant", required = false) Long id,
            @RequestParam(value = "type", required = false) String type
    ) {
        if (id == null && type == null) return getAll();
        if (type == null) return getByConsultantId(id);

        if (!Arrays.asList("interview", "selflearning").contains(type)) return null;

        List<ScheduleEntry> result = new ArrayList<>();
        List<ScheduleEntry> entries = (id == null) ? getAll() : getByConsultantId(id);

        for (ScheduleEntry entry : entries) {
            if (type.equals("interview") && entry.getClass() == Interview.class) {
                result.add(entry);
            } else if (type.equals("selflearning") && entry.getClass() == SelfLearning.class) {
                result.add(entry);
            }
        }
        return result;
    }

    @PostMapping("/selflearning")
    public ScheduleEntry saveSelfLearning(
            @RequestBody SelfLearningForm selfLearningForm
    ) throws ParseException {
        Consultant consultant = consultantRepo.findById(selfLearningForm.getConsultantId()).orElse(null);
        if (consultant == null) return null;

        Skill skill = skillRepo.findById(selfLearningForm.getSkill()).orElse(
                new Skill(selfLearningForm.getSkill())
        );
        skillRepo.save(skill);

        ScheduleEntry selfLearning = SelfLearning.builder()
                .description(selfLearningForm.getDescription())
                .skill(skill)
                .build();
        selfLearning.setDate(dateFormat.parse(selfLearningForm.getDateToBeDone()));
        selfLearning.setContent(selfLearningForm.getContent());
        selfLearning.setConsultant(consultant);
        selfLearning = scheduleEntryRepo.save(selfLearning);
        log.info("[Entity] ScheduleEntry: Self-learning #{} to be inserted.", selfLearning.getId());

        return selfLearning;
    }

    @PostMapping("/interview")
    public ScheduleEntry saveInterview(
            @RequestBody InterviewForm interviewForm
    ) throws ParseException {
        Opportunity opportunity = opportunityRepo.findById(interviewForm.getOpportunityId()).orElse(null);
        Consultant consultant = consultantRepo.findById(interviewForm.getConsultantId()).orElse(null);
        if (consultant == null || opportunity == null) return null;

        ScheduleEntry interview = Interview.builder()
                .time(interviewForm.getTime())
                .location(interviewForm.getLocation())
                .type(interviewForm.getType())
                .opportunity(opportunity)
                .build();
        interview.setContent(interviewForm.getContent());
        interview.setDate(dateFormat.parse(interviewForm.getDate()));
        interview.setConsultant(consultant);
        interview = scheduleEntryRepo.save(interview);
        log.info("[Entity] ScheduleEntry: Interview #{} to be inserted.", interview.getId());

        return interview;
    }

    @DeleteMapping
    public void deleteScheduleEntry(@RequestParam(value = "id") Long id) {
        if (scheduleEntryRepo.findById(id).orElse(null) == null) log.info("[Entity] ScheduleEntry #{} not found.", id);
        log.info("[Entity] ScheduleEntry #{} to be removed.", id);

        scheduleEntryRepo.deleteById(id);
    }

    @Data
    public static class SelfLearningForm {

        private String content;
        private Long consultantId;
        private String description;
        private String dateToBeDone;
        private String skill;
    }

    @Data
    public static class InterviewForm {

        private String content;
        private Long consultantId;
        private String time;
        private Long opportunityId;
        private String type;
        private String location;
        private String date;
    }
}
