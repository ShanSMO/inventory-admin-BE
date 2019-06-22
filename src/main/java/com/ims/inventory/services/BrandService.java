package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Brand;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BrandService {

    ResponseDto createUpdate(Brand brand);
    ResponseDto loadAll();

}
