package com.lutoke.pond.repository;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lutoke.pond.model.employee.*;
import com.lutoke.pond.model.opportunity.Client;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	Employee findByEmail(String email);
	List<Employee> findByFirstName(String firstName);
	List<Employee> findByLastName(String lastName);
	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	Employee findByEmailAndPassword(String email, String password);
}
