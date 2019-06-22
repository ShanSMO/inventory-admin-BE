package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.User;
import com.ims.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "sys/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createOrUpdateUser(@RequestBody User user){
        ResponseDto responseDto = userService.createOrUpdate(user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadUser(@RequestBody User user){
        ResponseDto responseDto = userService.loadById(user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> login(@RequestBody User user){
        ResponseDto responseDto = userService.login(user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-company", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadByCompany(@RequestBody User user){
        ResponseDto responseDto = userService.loadAllByCompanyId(user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }


}
