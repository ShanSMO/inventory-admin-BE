package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Brand;
import com.ims.inventory.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/brand")
@CrossOrigin("*")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll() {
        return new ResponseEntity<ResponseDto>(brandService.loadAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createUpdate(@RequestBody Brand brand) {
        return new ResponseEntity<ResponseDto>(brandService.createUpdate(brand), HttpStatus.OK);
    }
}
