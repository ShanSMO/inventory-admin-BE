package com.ims.inventory.services;

import com.ims.inventory.dtos.CustomDtos.CompanyCustomDto;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Company;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CompanyService {

    ResponseDto create(CompanyCustomDto company);
    ResponseDto update(CompanyCustomDto company);
    ResponseDto loadAll();
    ResponseDto loadById(CompanyCustomDto company);
    ResponseDto loadAllCount(CompanyCustomDto company);
    ResponseDto loadCompanyByStatus(CompanyCustomDto company);
    ResponseDto changeStatus(CompanyCustomDto company);

}
