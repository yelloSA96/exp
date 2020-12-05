package com.lutoke.pond.model.opportunity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.lutoke.pond.model.employee.Manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Getter @Setter @NoArgsConstructor
public class ManagersToClients {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	private Date startDate;
	private Date endDate;
}
