package com.lutoke.pond.model.employee;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public @Data class Skill {

	@Id
    private String name;
	private Boolean visible;

	public Skill(String name) {
	    this.name = name;
    }

	@PrePersist
    void prepare() {
	    if (visible == null) visible = true;
    }
}
