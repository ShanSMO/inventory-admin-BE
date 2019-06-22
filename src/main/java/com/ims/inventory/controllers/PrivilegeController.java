package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PrivilegeType;
import com.ims.inventory.services.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/privilege")
@CrossOrigin("*")
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody PrivilegeType privilegeType) {
        ResponseDto responseDto = privilegeService.loadAll(privilegeType);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "assign", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> assign(@RequestBody PrivilegeType privilegeType) {
        ResponseDto responseDto = privilegeService.assignPrivileges(privilegeType);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
