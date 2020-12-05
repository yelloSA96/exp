package com.lutoke.pond.model.opportunity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter @Setter
public class Placement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String position;
	//List<Skill> skill;
	private Date startDate;
	private Date endDate;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	//@JoinColumn(name = "consultant_id")
	//private Consultant consultant;
}
