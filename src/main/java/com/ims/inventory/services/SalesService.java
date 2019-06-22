package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Sales;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SalesService {

    ResponseDto createOrUpdate(Sales sales);
    ResponseDto loadAll(Sales sales);
    ResponseDto loadById(Sales sales);

}
