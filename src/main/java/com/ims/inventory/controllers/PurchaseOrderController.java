package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PurchaseOrder;
import com.ims.inventory.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/po")
@CrossOrigin("*")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createUpdate(@RequestBody PurchaseOrder purchaseOrder) {
        ResponseDto responseDto = purchaseOrderService.createOrUpdate(purchaseOrder);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody PurchaseOrder purchaseOrder) {
        ResponseDto responseDto = purchaseOrderService.loadAll(purchaseOrder);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
