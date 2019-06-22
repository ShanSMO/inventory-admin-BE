package com.ims.inventory.controllers;

import com.google.gson.Gson;
import com.ims.inventory.dtos.OauthResponse;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.SuperAdmin;
import com.ims.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "super-admin")
@CrossOrigin("*")
public class SuperAdminController {

    @Autowired
    UserService userService;




    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createOrUpdateUser(@RequestBody SuperAdmin user){
        ResponseDto responseDto = userService.createOrUpdateSuperAdmin(user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<OauthResponse> login(@RequestBody SuperAdmin user){
        OauthResponse oauthResponse = userService.loginSuperAdmin(user);
        return new ResponseEntity<OauthResponse>(oauthResponse, HttpStatus.OK);
    }
}
