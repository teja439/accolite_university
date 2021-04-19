package com.accolite.au.repositories;

import com.accolite.au.models.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<StudentGroup, Integer> {
    List<StudentGroup> findAllByBatch_BatchId(int batchId);
}
