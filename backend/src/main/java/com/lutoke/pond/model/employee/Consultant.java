package com.lutoke.pond.model.employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
@Entity
@Component 
@NoArgsConstructor
@Table(name="CONSULTANTS")
public @Data class Consultant extends Employee{
	
	String stream;
	@ManyToMany
	Set<Skill> skills = new HashSet<>();
	
	public Consultant(String stream) {
		super();
		this.stream = stream;
	}

	@Override
	public String toString() {
		return "Consultant [stream=" + stream + "]";
	}
	public void addSkill(Skill skill) {
		//you don't have to use a getter locally 
		this.skills.add(skill);
	}
	public void removeSkill(Skill skill) {
		this.skills.remove(skill);
	}

	public Consultant(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password=password;
		this.firstName=firstName;
		this.lastName = lastName;
	}

	public Consultant(String email, String password, String firstName, String lastName, String stream) {
		this.email = email;
		this.password=password;
		this.firstName=firstName;
		this.lastName = lastName;
		this.stream = stream;
	}

//	@PrePersist
//	void prepare() {
//		if (skills == null) skills = new ArrayList<>();
//	}
}
