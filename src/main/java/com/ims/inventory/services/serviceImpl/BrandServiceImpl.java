package com.ims.inventory.services.serviceImpl;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Brand;
import com.ims.inventory.models.QBrand;
import com.ims.inventory.repositories.BrandRepository;
import com.ims.inventory.services.BrandService;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    QBrand qBrand = QBrand.brand;

    @Override
    public ResponseDto createUpdate(Brand brand) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (brand.getBrandName() != null) {
            booleanBuilder.and(qBrand.brandName.equalsIgnoreCase(brand.getBrandName().trim()));
            Optional<Brand> brandOpt = brandRepository.findOne(booleanBuilder);
            if (brandOpt.isPresent()) {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Brand already exists");
            } else {
                brand.setBrandName(brand.getBrandName().trim());
                responseDto.setResponseObject(brandRepository.saveAndFlush(brand));
                responseDto.setStatus(Consts.JOB_SUCCESS);
                responseDto.setMessage("Successfully added");
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Invalid request");
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadAll() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseItems(brandRepository.findAll());
        return responseDto;
    }
}
