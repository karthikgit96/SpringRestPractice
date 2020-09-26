package com.infyrail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infyrail.entity.TrainEntity;

public interface TrainRepository extends JpaRepository<TrainEntity, Integer> {

}
