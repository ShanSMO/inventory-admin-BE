package com.ims.inventory.services;

import com.ims.inventory.dtos.CustomDtos.ManagementRequestDto;
import com.ims.inventory.dtos.SearchResponseDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DailyIncomeExpenseService {

    SearchResponseDto load(ManagementRequestDto managementRequestDto);

}
