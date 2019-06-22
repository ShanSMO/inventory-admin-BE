package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PurchaseOrderItem;
import com.ims.inventory.services.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "sys/po-item")
public class PurchaseOrderItemController {

    @Autowired
    PurchaseOrderItemService purchaseOrderItemService;

    @RequestMapping(value = "load-item", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadPoItem(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        ResponseDto responseDto = purchaseOrderItemService.loadItem(purchaseOrderItem);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
