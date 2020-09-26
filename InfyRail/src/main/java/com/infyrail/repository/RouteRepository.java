package com.infyrail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infyrail.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {
	
}
