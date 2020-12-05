package com.lutoke.pond.model.employee;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.OneToMany;


import com.lutoke.pond.model.opportunity.ManagersToClients;


@Entity
@Component
@Table(name = "MANAGERS")
public @Data class Manager extends Employee{

	@OneToMany(mappedBy = "manager")
	private Set<ManagersToClients> MTC = new HashSet<>();
	public Manager(String email, String password, String firstName, String lastName) {
	this.email = email;
	this.password=password;
	this.firstName=firstName;
	this.lastName = lastName;
	}
}
