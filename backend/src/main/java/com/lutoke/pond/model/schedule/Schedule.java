package com.lutoke.pond.model.schedule;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.lutoke.pond.model.employee.Consultant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@OneToOne(cascade = CascadeType.ALL)
	private Consultant consultant;
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
	private List<ScheduleEntry> scheduleEntries;
}
