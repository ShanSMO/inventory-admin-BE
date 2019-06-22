package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Customer;
import com.ims.inventory.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody Customer customer) {
        ResponseDto responseDto = customerService.create(customer);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> search(@RequestBody Customer customer) {
        ResponseDto responseDto = customerService.search(customer);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody Customer customer) {
        ResponseDto responseDto = customerService.loadAll(customer);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-id", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadById(@RequestBody Customer customer) {
        ResponseDto responseDto = customerService.loadById(customer);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "get-count", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> getCount(@RequestBody Customer customer) {
        ResponseDto responseDto = customerService.getCount(customer);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "is-exists", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> isCustomerExists(@RequestBody Customer customer) {
        ResponseDto responseDto = customerService.isExists(customer);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> remove(@RequestBody Customer customer) {
        ResponseDto responseDto = customerService.remove(customer);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
