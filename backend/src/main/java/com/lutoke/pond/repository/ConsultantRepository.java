package com.lutoke.pond.repository;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lutoke.pond.model.employee.*;
import com.lutoke.pond.model.opportunity.Client;


@Repository
public interface ConsultantRepository extends PagingAndSortingRepository<Consultant, Long> {

	Consultant findByEmail(String email);
	List<Consultant> findByFirstName(String firstName);
	List<Consultant> findByLastName(String lastName);
	List<Consultant> findByFirstNameAndLastName(String firstName, String lastName);

}
