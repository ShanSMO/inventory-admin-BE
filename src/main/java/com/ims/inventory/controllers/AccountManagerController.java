package com.ims.inventory.controllers;

import com.ims.inventory.dtos.CustomDtos.ManagementRequestDto;
import com.ims.inventory.dtos.SearchResponseDto;
import com.ims.inventory.services.DailyIncomeExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/account")
@CrossOrigin("*")
public class AccountManagerController {

    @Autowired
    DailyIncomeExpenseService dailyIncomeExpenseService;

    @RequestMapping(value = "daily-in-ex", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> dailyIncomeExpense(@RequestBody ManagementRequestDto managementRequestDto) {
        SearchResponseDto searchResponseDto = dailyIncomeExpenseService.load(managementRequestDto);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto,HttpStatus.OK);
    }

}
