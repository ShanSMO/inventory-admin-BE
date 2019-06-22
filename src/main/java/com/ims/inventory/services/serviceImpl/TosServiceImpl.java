package com.ims.inventory.services.serviceImpl;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.QTermsAndConditions;
import com.ims.inventory.models.TermsAndConditions;
import com.ims.inventory.repositories.TosRepository;
import com.ims.inventory.services.TosService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TosServiceImpl implements TosService {

    @Autowired
    TosRepository tosRepository;

    @Override
    public ResponseDto createUpdate(TermsAndConditions termsAndConditions) {
        ResponseDto responseDto = new ResponseDto();
        TermsAndConditions createdObject = tosRepository.saveAndFlush(termsAndConditions);
        responseDto.setResponseObject(createdObject);
        return responseDto;
    }

    @Override
    public ResponseDto loadAllByCompanyId(TermsAndConditions termsAndConditions) {
        ResponseDto responseDto = new ResponseDto();
        QTermsAndConditions qTermsAndConditions = QTermsAndConditions.termsAndConditions;
        BooleanBuilder booleanBuilder = new BooleanBuilder();


        if (termsAndConditions.getCompany() != null && termsAndConditions.getCompany().getId() != null) {
            booleanBuilder.and(qTermsAndConditions.company.id.eq(termsAndConditions.getCompany().getId()));
            List<TermsAndConditions> tosList = (List<TermsAndConditions>) tosRepository.findAll(booleanBuilder);
            responseDto.setResponseItems(tosList);
        } else {
            responseDto.setResponseItems(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto removeByCompanyId(TermsAndConditions termsAndConditions) {
        ResponseDto responseDto = new ResponseDto();
        QTermsAndConditions qTermsAndConditions = QTermsAndConditions.termsAndConditions;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (termsAndConditions.getCompany() != null && termsAndConditions.getCompany().getId() != null) {
            booleanBuilder.and(qTermsAndConditions.company.id.eq(termsAndConditions.getCompany().getId()));
            tosRepository.delete(termsAndConditions);
        } else {
            responseDto.setResponseItems(null);
        }
        return null;
    }
}
