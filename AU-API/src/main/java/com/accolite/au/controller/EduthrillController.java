package com.accolite.au.controller;

import com.accolite.au.dto.EduthrillSessionDTO;
import com.accolite.au.dto.EduthrillTestDTO;
import com.accolite.au.services.EduthrillService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduthrill")
public class EduthrillController {
    private final EduthrillService eduthrillService;

    public EduthrillController(EduthrillService eduthrillService) {
        this.eduthrillService = eduthrillService;
    }

    @PostMapping({"/uploadTest/{batchId}"})
    public ResponseEntity<ObjectNode> uploadTest(@RequestParam(name = "eduthrillTestFile") MultipartFile eduthrillTestFile, @RequestParam(name = "eduthrillTestName") String eduthrillTestName, @RequestParam(name = "eduthrillTestId") String eduthrillTestId, @PathVariable(name = "batchId") int batchId) {
        return new ResponseEntity(eduthrillService.uploadTest(eduthrillTestFile, new EduthrillTestDTO(eduthrillTestName, eduthrillTestId), batchId), HttpStatus.CREATED);
    }

    @GetMapping({"/{eduthrillTestId}"})
    public ResponseEntity<EduthrillTestDTO> getEduthrillTest(@PathVariable(name = "eduthrillTestId") int eduthrillTestId){
        return new ResponseEntity(eduthrillService.getEduthrillTest(eduthrillTestId), HttpStatus.OK);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<EduthrillTestDTO>> getAllEduthrillTest(){
        return new ResponseEntity(eduthrillService.getAllEduthrillTest(), HttpStatus.OK);
    }

    @GetMapping({"/sessions/all/{eduthrillTestId}"})
    public ResponseEntity<List<EduthrillSessionDTO>> getAllEduthrillSessionsForTest(@PathVariable(name = "eduthrillTestId") int eduthrillTestId){
        return new ResponseEntity(eduthrillService.getAllEduthrillSessionsForTest(eduthrillTestId), HttpStatus.OK);
    }

    @GetMapping({"/session/{eduthrillSessionId}"})
    public ResponseEntity<EduthrillTestDTO> getEduthrillSession(@PathVariable(name = "eduthrillSessionId") int eduthrillSessionId){
        return new ResponseEntity(eduthrillService.getEduthrillSession(eduthrillSessionId), HttpStatus.OK);
    }

    @GetMapping({"/sessions/student/{studentId}"})
    public ResponseEntity<List<EduthrillSessionDTO>> getAllEduthrillSessionsForStudent(@PathVariable(name = "studentId") int studentId){
        return new ResponseEntity(eduthrillService.getAllEduthrillSessionsForStudent(studentId), HttpStatus.OK);
    }
}
