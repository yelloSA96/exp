package com.lutoke.pond.model.opportunity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Component
@Getter @Setter @NoArgsConstructor
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String name;
	private boolean signed;
	@OneToMany(mappedBy = "client")
	private Set<ManagersToClients> MTC = new HashSet<>();
	@OneToMany(mappedBy = "client")
	private Set<Placement> placements = new HashSet<>();
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private Set<Opportunity> opportunities = new HashSet<>();
}
