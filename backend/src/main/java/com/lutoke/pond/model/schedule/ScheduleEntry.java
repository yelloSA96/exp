package com.lutoke.pond.model.schedule;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lutoke.pond.model.employee.Consultant;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ScheduleEntry {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Date date;
	private String content;

	@ManyToOne
	private Consultant consultant;
	@JsonIgnore
	@ManyToOne
	private Schedule schedule;

	@PrePersist
	public void prepare() {
		if (date == null) date = new Date();
	}
}
