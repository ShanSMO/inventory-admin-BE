package com.ims.inventory.controllers;

import com.ims.inventory.dtos.CustomDtos.CompanyCustomDto;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Company;
import com.ims.inventory.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/company")
@CrossOrigin("*")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> create(@RequestBody CompanyCustomDto company) {
        ResponseDto responseDto = companyService.create(company);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> update(@RequestBody CompanyCustomDto company) {
        ResponseDto responseDto = companyService.update(company);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll() {
        ResponseDto responseDto = companyService.loadAll();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-id", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadById(@RequestBody CompanyCustomDto company) {
        ResponseDto responseDto = companyService.loadById(company);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load-all-count")
    public ResponseEntity<ResponseDto> loadAllCount(@RequestBody CompanyCustomDto company) {
        ResponseDto responseDto = companyService.loadAllCount(company);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load-all-by-status")
    public ResponseEntity<ResponseDto> loadAllCountStat(@RequestBody CompanyCustomDto company) {
        ResponseDto responseDto = companyService.loadCompanyByStatus(company);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "change-status")
    public ResponseEntity<ResponseDto> changeStatus(@RequestBody CompanyCustomDto company) {
        ResponseDto responseDto = companyService.changeStatus(company);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
