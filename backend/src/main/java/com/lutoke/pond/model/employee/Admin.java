package com.lutoke.pond.model.employee;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Entity
@Component 
@NoArgsConstructor
@Table(name = "ADMINISTRATORS")
public class Admin extends Employee {
	public Admin(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password=password;
		this.firstName=firstName;
		this.lastName = lastName;
	}
}
