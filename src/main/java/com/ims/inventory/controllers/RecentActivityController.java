package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.RecentActivity;
import com.ims.inventory.services.RecentActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/recent")
@CrossOrigin("*")
public class RecentActivityController {

    @Autowired
    RecentActivityService recentActivityService;

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody RecentActivity recentActivity) {
        return new ResponseEntity<ResponseDto>(recentActivityService.loadAll(recentActivity), HttpStatus.OK);
    }




}
