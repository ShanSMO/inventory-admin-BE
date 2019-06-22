package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.TermsAndConditions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TosService {

    ResponseDto createUpdate(TermsAndConditions termsAndConditions);
    ResponseDto loadAllByCompanyId(TermsAndConditions termsAndConditions);
    ResponseDto removeByCompanyId(TermsAndConditions termsAndConditions);

}
