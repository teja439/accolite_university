package com.accolite.au.controller;

import com.accolite.au.dto.*;
import com.accolite.au.services.TrainingService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping({"/markAttendance"})
    public ResponseEntity<TrainingDTO> markAndUpdateAttendance(@Valid @RequestBody TrainingDTO trainingDTO) {
        return new ResponseEntity(trainingService.markAndUpdateTrainingData(trainingDTO, 'A'), HttpStatus.CREATED);
    }

    @PostMapping({"/assignMarks"})
    public ResponseEntity<TrainingDTO> markAndUpdateMarks(@Valid @RequestBody TrainingDTO trainingDTO) {
        return new ResponseEntity(trainingService.markAndUpdateTrainingData(trainingDTO, 'M'), HttpStatus.CREATED);
    }

    @GetMapping({"/all/{batchId}"})
    public ResponseEntity<ObjectNode> getAllTrainingData(@PathVariable(name="batchId") int batchId, @RequestParam(name="type") char type){
        return new ResponseEntity(trainingService.getAllTrainingData(type, batchId), HttpStatus.OK);
    }

    @GetMapping({"/"})
    public ResponseEntity<TrainingDTO> getTrainingData(@RequestParam(name = "sessionId") int sessionId, @RequestParam(name = "studentId") int studentId){
        return new ResponseEntity(trainingService.getTrainingData(sessionId, studentId), HttpStatus.OK);
    }
}
