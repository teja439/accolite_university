package com.accolite.au.controller;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.BatchService;
import com.accolite.au.services.MailerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<BatchDTO> addBatch(@Valid @RequestBody BatchDTO batch) {
        return new ResponseEntity(batchService.addBatch(batch), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<BatchDTO> updateBatch(@Valid @RequestBody BatchDTO batch) throws IllegalAccessException {
        return new ResponseEntity(batchService.updateBatch(batch), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<BatchDTO>> getAllBatches(){
        return new ResponseEntity(batchService.getAllBatches(), HttpStatus.OK);
    }

    @GetMapping({"/{batchId}"})
    public ResponseEntity<BatchDTO> getBatch(@PathVariable(required = true, name = "batchId") int batchId){
        return new ResponseEntity(batchService.getBatch(batchId), HttpStatus.OK);
    }

    @DeleteMapping({"/{batchId}"})
    public ResponseEntity<SuccessResponseDTO> deleteBatch(@PathVariable(required = true, name="batchId") int batchId){
        return new ResponseEntity(batchService.deleteBatch(batchId), HttpStatus.OK);
    }
}
