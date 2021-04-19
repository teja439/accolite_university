package com.accolite.au.controller;

import com.accolite.au.dto.BusinessUnitDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.BusinessUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/businessUnit")
public class BusinessUnitController {
    private final BusinessUnitService businessUnitService;

    public BusinessUnitController(BusinessUnitService businessUnitService) {
        this.businessUnitService = businessUnitService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<BusinessUnitDTO> addBusinessUnit(@Valid @RequestBody BusinessUnitDTO businessUnitDTO) {
        return new ResponseEntity(businessUnitService.addBusinessUnit(businessUnitDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<BusinessUnitDTO> updateBusinessUnit(@Valid @RequestBody BusinessUnitDTO businessUnitDTO) {
        return new ResponseEntity(businessUnitService.updateBusinessUnit(businessUnitDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<BusinessUnitDTO>> getAllBusinessUnits(){
        return new ResponseEntity(businessUnitService.getAllBusinessUnits(), HttpStatus.OK);
    }
    
    @GetMapping({"/{businessUnitId}"})
    public ResponseEntity<BusinessUnitDTO> getBusinessUnit(@PathVariable(required = true, name = "businessUnitId") int businessUnitId){
        return new ResponseEntity(businessUnitService.getBusinessUnit(businessUnitId), HttpStatus.OK);
    }

    @DeleteMapping({"/{businessUnitId}"})
    public ResponseEntity<SuccessResponseDTO> deleteBusinessUnit(@PathVariable(required = true, name="businessUnitId") int businessUnitId){
        return new ResponseEntity(businessUnitService.deleteBusinessUnit(businessUnitId), HttpStatus.OK);
    }
}
