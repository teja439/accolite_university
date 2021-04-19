package com.accolite.au.repositories;

import com.accolite.au.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    List<Session> findAllByBatch_BatchId(int batchId);

    @Query("SELECT s.sessionName, s.sessionId from Session as s")
    List<String[]> findAllSessions();

    List<Session> findAllByBatch_BatchIdOrderByStartDateAscDaySlotDesc(int batchId);
    
    @Query("SELECT s.sessionName, s.sessionId from Session as s WHERE s.batch.batchId = :batchId")
    List<String[]> findAllSessionsByBatchId(int batchId);
}
