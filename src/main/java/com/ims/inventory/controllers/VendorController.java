package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Vendor;
import com.ims.inventory.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/vendor")
@CrossOrigin("*")
public class VendorController {

    @Autowired
    VendorService vendorService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody Vendor vendor) {
        ResponseDto responseDto = vendorService.createUpdate(vendor);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> search(@RequestBody Vendor vendor) {
        ResponseDto responseDto = vendorService.search(vendor);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody Vendor vendor) {
        ResponseDto responseDto = vendorService.loadAll(vendor);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-id", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadById(@RequestBody Vendor vendor) {
        ResponseDto responseDto = vendorService.loadById(vendor);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "get-count", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> getCount(@RequestBody Vendor vendor) {
        ResponseDto responseDto = vendorService.loadAllCount(vendor);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "add-to-black-list", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> addToBlackList(@RequestBody Vendor vendor) {
        ResponseDto responseDto = vendorService.addToBlackList(vendor);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> remove(@RequestBody Vendor vendor) {
        ResponseDto responseDto = vendorService.remove(vendor);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
