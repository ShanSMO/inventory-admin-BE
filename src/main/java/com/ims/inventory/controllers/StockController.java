package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Stock;
import com.ims.inventory.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/stock")
@CrossOrigin("*")
public class StockController {

    @Autowired
    StockService stockService;

    @RequestMapping(value = "create-update",method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createOrUpdate(@RequestBody Stock stock) {
        return new ResponseEntity<ResponseDto>(stockService.addUpdateStockItem(stock), HttpStatus.OK);
    }

    @RequestMapping(value = "load-all",method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> findStockItems() {
        return new ResponseEntity<ResponseDto>(stockService.loadStock(), HttpStatus.OK);
    }

    @RequestMapping(value = "load-all-by-group",method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAllByGroup(@RequestBody Stock stock) {
        return new ResponseEntity<ResponseDto>(stockService.loadStockForList(stock), HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-barcode",method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadByBarcode(@RequestBody Stock stock) {
        return new ResponseEntity<ResponseDto>(stockService.loadByBarcode(stock), HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-product",method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadByProduct(@RequestBody Stock stock) {
        return new ResponseEntity<ResponseDto>(stockService.loadByProduct(stock), HttpStatus.OK);
    }



}
