package com.accolite.au.repositories;

import com.accolite.au.models.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Integer> {

    List<BusinessUnit> findAllByBuHeadEmail(@Param("buHeadEmail") String buHeadEmail);
}
