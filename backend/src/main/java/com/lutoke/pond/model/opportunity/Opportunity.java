package com.lutoke.pond.model.opportunity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.lutoke.pond.model.employee.Skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Opportunity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String position;
	@ManyToMany
	private List<Skill> skills;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	private Boolean visible;

	public void addSkill(Skill skill) {
		skills.add(skill);
	}

	public void removeSkill(Skill skill) {
		skills.remove(skill);
	}

	@PrePersist
	void prepare() {
		if (skills == null) skills = new ArrayList<>();
		if (visible ==  null) visible = true;
	}
}
