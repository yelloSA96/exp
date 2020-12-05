package com.lutoke.pond.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

import com.lutoke.pond.model.schedule.Schedule;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {

	Schedule findByConsultant_EmployeeID(long id);
}
