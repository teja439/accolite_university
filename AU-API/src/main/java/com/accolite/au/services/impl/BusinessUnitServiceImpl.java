package com.accolite.au.services.impl;

import com.accolite.au.dto.BusinessUnitDTO;
import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.mappers.BusinessUnitMapper;
import com.accolite.au.models.BusinessUnit;
import com.accolite.au.repositories.BusinessUnitRepository;
import com.accolite.au.services.BusinessUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {
    private final BusinessUnitRepository businessUnitRepository;
    private final BusinessUnitMapper businessUnitMapper;

    public BusinessUnitServiceImpl(BusinessUnitRepository businessUnitRepository, BusinessUnitMapper businessUnitMapper) {
        this.businessUnitRepository = businessUnitRepository;
        this.businessUnitMapper = businessUnitMapper;
    }

    @Override
    public List<BusinessUnitDTO> getAllBusinessUnits(){
        return businessUnitMapper.toBusinessUnitDTOs(businessUnitRepository.findAll());
    }

    @Override
    public BusinessUnitDTO getBusinessUnit(int businessUnitId){
        if(businessUnitRepository.existsById(businessUnitId)) {
            return businessUnitMapper.toBusinessUnitDTO(businessUnitRepository.getOne(businessUnitId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Business Unit with id : " + businessUnitId + " not Found");
    }

    @Override
    public BusinessUnitDTO addBusinessUnit(BusinessUnitDTO businessUnitDTO) {
        BusinessUnit businessUnit = businessUnitMapper.toBusinessUnit(businessUnitDTO);
        return businessUnitMapper.toBusinessUnitDTO(businessUnitRepository.saveAndFlush(businessUnit));
    }

    @Override
    public SuccessResponseDTO deleteBusinessUnit(int businessUnitId) {
        if(businessUnitRepository.existsById(businessUnitId)){
            businessUnitRepository.deleteById(businessUnitId);
            return new SuccessResponseDTO("Business Unit with id : " + businessUnitId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Business Unit with id : " + businessUnitId + " not Found");
    }

    @Override
    public BusinessUnitDTO updateBusinessUnit(BusinessUnitDTO businessUnitDTO) {
        if(businessUnitRepository.existsById(businessUnitDTO.getBuId())) {
            BusinessUnit businessUnit = businessUnitMapper.toBusinessUnit(businessUnitDTO);
            return businessUnitMapper.toBusinessUnitDTO(businessUnitRepository.saveAndFlush(businessUnit));
        }
        throw new CustomEntityNotFoundExceptionDTO("Business Unit with id : " + businessUnitDTO.getBuId() + " not Found");
    }
}
