package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.TermsAndConditions;
import com.ims.inventory.services.TosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/tos")
@CrossOrigin("*")
public class TosController {

    @Autowired
    TosService tosService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody TermsAndConditions termsAndConditions) {
        ResponseDto responseDto = tosService.createUpdate(termsAndConditions);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load-for-company", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadForCompany(@RequestBody TermsAndConditions termsAndConditions) {
        ResponseDto responseDto = tosService.loadAllByCompanyId(termsAndConditions);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "remove-for-company", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> removeForCompany(@RequestBody TermsAndConditions termsAndConditions) {
        ResponseDto responseDto = tosService.removeByCompanyId(termsAndConditions);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
