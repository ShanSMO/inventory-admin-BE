package com.ims.inventory.controllers;

import com.ims.inventory.dtos.CustomDtos.ChartDataRequestDto;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Company;
import com.ims.inventory.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/stat")
@CrossOrigin("*")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value = "load-all",method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadStatisticData(@RequestBody Company company) {
        ResponseDto responseDto = statisticService.loadStaticData(company);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-analytic-data",method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadChartData(@RequestBody ChartDataRequestDto chartDataRequestDto) {
        ResponseDto responseDto = statisticService.loadAnalyticData(chartDataRequestDto);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

}
