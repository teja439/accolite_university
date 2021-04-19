package com.accolite.au.repositories;

import com.accolite.au.models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

    List<Batch> findAllByOrderByBatchName();
}
