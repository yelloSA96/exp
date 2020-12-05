package com.lutoke.pond.model.schedule;

import java.util.Date;

import javax.persistence.*;

import com.lutoke.pond.model.employee.Consultant;
import com.lutoke.pond.model.opportunity.Opportunity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public @Data class Interview extends ScheduleEntry{

	private String time;
	private String location;
	private String type;
	@ManyToOne
	private Opportunity opportunity;

}
