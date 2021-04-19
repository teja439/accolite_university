package com.accolite.au.repositories;

import com.accolite.au.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    List<Trainer> findAllByEmailId(@Param("email") String email);

    @Query("SELECT trainer.businessUnit.buName as bUName, trainer.businessUnit.buId as buId," +
            "COUNT(trainer) as trainerPerBU from Trainer as trainer group by trainer.businessUnit.buId")
    List<Map<String, ?>> countTrainerByBusinessUnit();
}