package com.accolite.au.services;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SessionService {

    SuccessResponseDTO deleteSession(int SessionId);

    SessionDTO addOrUpdateSession(SessionDTO sessionDTO);

    List<SessionDTO> getAllSessions(int batchId);

    SessionDTO getSession(int sessionId);

    void uploadFile(MultipartFile sessionsFile, int batchId);

    //SessionDTO updateSession(SessionDTO sessionDTO);
}
