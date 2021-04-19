package com.accolite.au.controller;

import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<TrainerDTO> addTrainerToBatch(@Valid @RequestBody TrainerDTO trainerDTO) {
        return new ResponseEntity(trainerService.addToBatchOrUpdateTrainer(trainerDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<TrainerDTO> updateTrainer(@Valid @RequestBody TrainerDTO trainerDTO) {
        return new ResponseEntity(trainerService.addToBatchOrUpdateTrainer(trainerDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        return new ResponseEntity(trainerService.getAllTrainers(), HttpStatus.OK);
    }

    @GetMapping({"/allByBUCount"})
    public ResponseEntity<List<Map<String, Object>>> getAllTrainerPerBUCount() {
        return new ResponseEntity(trainerService.getAllTrainerPerBUCount(), HttpStatus.OK);
    }

    @GetMapping({"/{trainerId}"})
    public ResponseEntity<TrainerDTO> getTrainer(@PathVariable(required = true, name = "trainerId") int trainerId){
        return new ResponseEntity(trainerService.getTrainer(trainerId), HttpStatus.OK);
    }

    @DeleteMapping({"/{trainerId}"})
    public ResponseEntity<SuccessResponseDTO> deleteTrainer(@PathVariable(required = true, name="trainerId") int trainerId){
        return new ResponseEntity(trainerService.deleteTrainer(trainerId), HttpStatus.OK);
    }
}
