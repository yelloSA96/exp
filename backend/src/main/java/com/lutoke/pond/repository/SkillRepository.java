package com.lutoke.pond.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lutoke.pond.model.employee.Skill;

public interface SkillRepository extends PagingAndSortingRepository<Skill, String>{
}
