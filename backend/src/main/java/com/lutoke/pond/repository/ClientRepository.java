package com.lutoke.pond.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lutoke.pond.model.opportunity.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
	List<Client> findByName(String name);
}
