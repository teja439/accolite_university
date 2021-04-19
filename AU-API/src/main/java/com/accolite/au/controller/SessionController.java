package com.accolite.au.controller;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.MailerService;
import com.accolite.au.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;
    private final MailerService mailerService;

    public SessionController(SessionService sessionService, MailerService mailerService) {
        this.sessionService = sessionService;
        this.mailerService = mailerService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<SessionDTO> addSession(@Valid @RequestBody SessionDTO sessionDTO) {
        return new ResponseEntity(sessionService.addOrUpdateSession(sessionDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<SessionDTO> updateSession(@Valid @RequestBody SessionDTO sessionDTO) {
        return new ResponseEntity(sessionService.addOrUpdateSession(sessionDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<SessionDTO>> getAllSessions(@RequestParam(required = true, name = "batchId") int batchId){
        return new ResponseEntity(sessionService.getAllSessions(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{sessionId}"})
    public ResponseEntity<SessionDTO> getSession(@PathVariable(required = true, name = "sessionId") int sessionId){
        return new ResponseEntity(sessionService.getSession(sessionId), HttpStatus.OK);
    }

    @DeleteMapping({"/{sessionId}"})
    public ResponseEntity<SuccessResponseDTO> deleteSession(@PathVariable(required = true, name="sessionId") int sessionId){
        return new ResponseEntity(sessionService.deleteSession(sessionId), HttpStatus.OK);
    }

    @PostMapping(value = {"/bulkAdd"})
    public ResponseEntity<SuccessResponseDTO> addBulkSessions(@RequestParam(name = "sessionsFile") MultipartFile sessionsFile, @RequestParam(name="batchId") int batchId) throws IOException {
        sessionService.uploadFile(sessionsFile, batchId);
        return new ResponseEntity(new SuccessResponseDTO("File will be uploaded soon, and you can refresh the page to see the updated data !!", HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping({"/sendMail/{sessionId}"})
    public ResponseEntity<SuccessResponseDTO> sendMail(@PathVariable(name="sessionId") int sessionId) throws IOException {
        mailerService.SendMail(sessionId);
        return new ResponseEntity(new SuccessResponseDTO("Mail Will Be Sent Soon!! We have initiated the process", HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
