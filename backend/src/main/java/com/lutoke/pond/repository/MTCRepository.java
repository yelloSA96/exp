package com.lutoke.pond.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lutoke.pond.model.opportunity.ManagersToClients;

public interface MTCRepository extends PagingAndSortingRepository<ManagersToClients, Long>{

}
