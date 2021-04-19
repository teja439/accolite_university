package com.accolite.au.services;

import com.accolite.au.dto.BusinessUnitDTO;
import com.accolite.au.dto.SuccessResponseDTO;

import java.util.List;

public interface BusinessUnitService {
    List<BusinessUnitDTO> getAllBusinessUnits();

    BusinessUnitDTO getBusinessUnit(int businessUnitId);

    BusinessUnitDTO addBusinessUnit(BusinessUnitDTO businessUnitDTO);

    SuccessResponseDTO deleteBusinessUnit(int businessUnitId);

    BusinessUnitDTO updateBusinessUnit(BusinessUnitDTO businessUnitDTO);
}
