package com.lutoke.pond.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lutoke.pond.model.opportunity.Client;
import com.lutoke.pond.model.opportunity.Opportunity;

@Repository
public interface OpportunityRepository extends PagingAndSortingRepository<Opportunity, Long> {
	List<Opportunity> findByPosition(String position);
	
	List<Client> findByClient(Client client);
}
