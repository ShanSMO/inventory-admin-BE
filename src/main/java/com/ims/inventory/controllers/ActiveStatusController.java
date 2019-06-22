package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.enums.ActiveStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "sys/status")
@CrossOrigin("*")
public class ActiveStatusController {

    @RequestMapping(value = "load-all", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> loadAllStatus() {
        ResponseDto responseDto = new ResponseDto();
        HashMap<String, Object> statusHashMap = new HashMap<>();
        List<HashMap<String, Object>> hashMapList = new ArrayList<>();
        for (ActiveStatus activeStatus: ActiveStatus.values()){
            statusHashMap = new HashMap<>();
            statusHashMap.put("key", activeStatus);
            statusHashMap.put("value", activeStatus.toString().toLowerCase());
            hashMapList.add(statusHashMap);
        }

        responseDto.setResponseItems(hashMapList);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
}
