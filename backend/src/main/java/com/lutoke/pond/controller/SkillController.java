package com.lutoke.pond.controller;

import lombok.extern.log4j.Log4j2;

import com.lutoke.pond.model.employee.Skill;
import com.lutoke.pond.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/skill")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

    private final SkillRepository skillRepo;

    @Autowired
    public SkillController(
            SkillRepository skillRepo
    ) {
        this.skillRepo = skillRepo;
    }

    @GetMapping
    public List<Skill> getSkill(@RequestParam(value = "name", required = false) String name) {
        Iterable<Skill> skills = (name == null) ? skillRepo.findAll() :
                skillRepo.findAllById(Collections.singletonList(name));

        List<Skill> result = new ArrayList<>();
        for (Skill skill: skills) {
            if (skill.getVisible()) result.add(skill);
        }
        return result;
    }

    @PostMapping
    public Skill saveSkill(@RequestParam(value = "name") String name) {
        Skill skill = skillRepo.findById(name).orElse(null);
        if (skill != null) return skill;
        log.info("[Entity] Skill {} to be inserted.", name);

        return skillRepo.save(new Skill(name));
    }

    @DeleteMapping
    public void deleteSkillByName(@RequestParam(value = "name") String name) {
        Skill skill = skillRepo.findById(name).orElse(null);
        if (skill == null) return;
        log.info("[Entity] Skill {} to be invisible.", name);
        skill.setVisible(false);
        skillRepo.save(skill);
    }
}
