package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Sales;
import com.ims.inventory.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/sales")
@CrossOrigin("*")
public class SalesController {


    @Autowired
    SalesService salesService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createOrUpdate(@RequestBody Sales sales) {
        return new ResponseEntity<ResponseDto>(salesService.createOrUpdate(sales), HttpStatus.OK);
    }

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody Sales sales) {
        return new ResponseEntity<ResponseDto>(salesService.loadAll(sales), HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-id", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadById(@RequestBody Sales sales) {
        return new ResponseEntity<ResponseDto>(salesService.loadById(sales), HttpStatus.OK);
    }
}
