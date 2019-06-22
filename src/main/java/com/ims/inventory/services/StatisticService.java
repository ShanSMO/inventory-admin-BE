package com.ims.inventory.services;

import com.ims.inventory.dtos.CustomDtos.ChartDataRequestDto;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Company;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StatisticService {

    public ResponseDto loadStaticData(Company company);
    public ResponseDto loadAnalyticData(ChartDataRequestDto chartDataRequestDto);

}
