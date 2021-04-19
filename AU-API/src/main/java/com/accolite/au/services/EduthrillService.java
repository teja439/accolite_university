package com.accolite.au.services;

import com.accolite.au.dto.EduthrillSessionDTO;
import com.accolite.au.dto.EduthrillTestDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EduthrillService {
    ObjectNode uploadTest(MultipartFile sessionsFile, EduthrillTestDTO eduthrillTestDTO, int batchId);

    EduthrillTestDTO getEduthrillTest(int eduthrillTestId);

    List<EduthrillTestDTO> getAllEduthrillTest();

    List<EduthrillSessionDTO> getAllEduthrillSessionsForTest(int eduthrillTestId);

    EduthrillSessionDTO getEduthrillSession(int eduthrillSessionId);

    List<EduthrillSessionDTO> getAllEduthrillSessionsForStudent(int studentId);
}
