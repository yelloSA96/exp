package com.lutoke.pond.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lutoke.pond.model.employee.Consultant;
import com.lutoke.pond.model.employee.Skill;
import com.lutoke.pond.model.opportunity.Opportunity;
import com.lutoke.pond.model.schedule.ScheduleEntry;
import com.lutoke.pond.repository.*;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@Log4j2
public class ScheduleControllerTest {

    @Mock
    ConsultantRepository consultantRepo;
    @Mock
    OpportunityRepository opportunityRepo;
    @Mock
    ScheduleEntryRepository scheduleEntryRepo;
    @Mock
    SkillRepository skillRepo;
    @Mock
    Iterable<ScheduleEntry> scheduleEntryIterable;
    @Mock
    Iterator<ScheduleEntry> scheduleEntryIterator;
    @Mock
    ScheduleController.SelfLearningForm selfLearningForm;
    @Mock
    ScheduleController.InterviewForm interviewForm;
    Consultant consultant;
    Skill skill;
    Opportunity opportunity;
    Optional<Consultant> optionalConsultant;
    Optional<Skill> optionalSkill;
    Optional<Opportunity> optionalOpportunity;

    ScheduleController controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controller = new ScheduleController(consultantRepo, opportunityRepo, scheduleEntryRepo, skillRepo);
        consultant = new Consultant();
        skill = new Skill();
        opportunity = new Opportunity();
        optionalConsultant = Optional.of(consultant);
        optionalSkill = Optional.of(skill);
        optionalOpportunity = Optional.of(opportunity);
    }

    @Test
    public void that_controller_init_correctly() {
        assertNotNull(controller);
    }

    @Test
    public void that_gets_all_scheduleEntries() {
        when(scheduleEntryRepo.findAll()).thenReturn(scheduleEntryIterable);
        when(scheduleEntryIterable.iterator()).thenReturn(scheduleEntryIterator);

        controller.getScheduleEntryByConsultantIdAndType(null, null);

        verify(scheduleEntryRepo, times(1)).findAll();
        verify(scheduleEntryIterable, times(1)).iterator();
        verify(scheduleEntryIterator, times(1)).hasNext();
    }

    @Test
    public void that_gets_scheduleEntries_by_consultantId_only() {
        when(scheduleEntryRepo.findByConsultant_EmployeeID(99L)).thenReturn(scheduleEntryIterable);
        when(scheduleEntryIterable.iterator()).thenReturn(scheduleEntryIterator);

        controller.getScheduleEntryByConsultantIdAndType(99L, null);

        verify(scheduleEntryRepo, times(1)).findByConsultant_EmployeeID(99L);
        verify(scheduleEntryIterable, times(1)).iterator();
        verify(scheduleEntryIterator, times(1)).hasNext();
    }

    @Test
    public void that_gets_scheduleEntries_by_type_interview_only() {
        when(scheduleEntryRepo.findAll()).thenReturn(scheduleEntryIterable);
        when(scheduleEntryIterable.iterator()).thenReturn(scheduleEntryIterator);

        controller.getScheduleEntryByConsultantIdAndType(null, "interview");

        verify(scheduleEntryRepo, times(1)).findAll();
        verify(scheduleEntryIterable, times(1)).iterator();
        verify(scheduleEntryIterator, times(1)).hasNext();
    }

    @Test
    public void that_gets_scheduleEntries_by_consultantId_and_type() {
        when(scheduleEntryRepo.findByConsultant_EmployeeID(99L)).thenReturn(scheduleEntryIterable);
        when(scheduleEntryIterable.iterator()).thenReturn(scheduleEntryIterator);

        controller.getScheduleEntryByConsultantIdAndType(99L, "interview");

        verify(scheduleEntryRepo, times(1)).findByConsultant_EmployeeID(99L);
        verify(scheduleEntryIterable, times(1)).iterator();
        verify(scheduleEntryIterator, times(1)).hasNext();
    }

    @Test
    @Ignore
    public void that_saves_selflearning() throws ParseException {
        when(selfLearningForm.getConsultantId()).thenReturn(99L);
        when(consultantRepo.findById(99L)).thenReturn(optionalConsultant);
        when(selfLearningForm.getSkill()).thenReturn("skill");
        when(skillRepo.findById("skill")).thenReturn(optionalSkill);
        when(selfLearningForm.getDateToBeDone()).thenReturn("1840-01-01");

        controller.saveSelfLearning(selfLearningForm);

        verify(selfLearningForm, times(1)).getConsultantId();
        verify(consultantRepo, times(1)).findById(99L);
        verify(selfLearningForm, times(2)).getSkill();
        verify(skillRepo, times(1)).findById("skill");
        verify(skillRepo, times(1)).save(skill);
        verify(selfLearningForm, times(1)).getDateToBeDone();
        verify(selfLearningForm, times(1)).getContent();
        verify(scheduleEntryRepo, times(1)).save(anyObject());
    }

    @Test 
    @Ignore
    public void that_saves_interview() throws ParseException {
        when(interviewForm.getOpportunityId()).thenReturn(99L);
        when(opportunityRepo.findById(99L)).thenReturn(optionalOpportunity);
        when(interviewForm.getConsultantId()).thenReturn(98L);
        when(consultantRepo.findById(98L)).thenReturn(optionalConsultant);
        when(interviewForm.getDate()).thenReturn("1840-01-01");

        controller.saveInterview(interviewForm);

        verify(interviewForm, times(1)).getOpportunityId();
        verify(opportunityRepo, times(1)).findById(99L);
        verify(interviewForm, times(1)).getConsultantId();
        verify(consultantRepo, times(1)).findById(98L);
        verify(interviewForm, times(1)).getContent();
        verify(interviewForm, times(1)).getDate();
        verify(scheduleEntryRepo, times(1)).save(anyObject());
    }
}
