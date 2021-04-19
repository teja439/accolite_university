package com.accolite.au.repositories;

import com.accolite.au.models.EduthrillTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EduthrillTestRepository extends JpaRepository<EduthrillTest, Integer> {

    @Query("select test from EduthrillTest as test where test.testId = :testId or test.testName = :testName")
    EduthrillTest ifTestExists(String testId, String testName);
}