package com.ims.inventory.services.serviceImpl;

import com.ims.inventory.dtos.CustomDtos.ChartDataRequestDto;
import com.ims.inventory.dtos.CustomDtos.ChartResponseDto;
import com.ims.inventory.dtos.CustomDtos.SalesStaticDataSto;
import com.ims.inventory.dtos.CustomDtos.StatisticDataDto;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Company;
import com.ims.inventory.models.QSales;
import com.ims.inventory.models.QSalesOrderItem;
import com.ims.inventory.models.Sales;
import com.ims.inventory.repositories.*;
import com.ims.inventory.services.StatisticService;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SalesRepository salesRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM");

    @Override
    public ResponseDto loadStaticData(Company company) {
        ResponseDto responseDto = new ResponseDto();
        StatisticDataDto statisticDataDto = new StatisticDataDto();

        statisticDataDto.setCategoryCount(categoryRepository.countByCompany(company));
        statisticDataDto.setCustomerCount(customerRepository.countByCompany(company));
        statisticDataDto.setProductCount(productRepository.countByCompany(company));
        statisticDataDto.setSalesCount(salesRepository.countByCompany(company));
        statisticDataDto.setSupplierCount(vendorRepository.countByCompany(company));

        responseDto.setResponseObject(statisticDataDto);
        return responseDto;
    }

    @Override
    public ResponseDto loadAnalyticData(ChartDataRequestDto chartDataRequestDto) {
        ResponseDto responseDto = new ResponseDto();

        if (chartDataRequestDto.getCompanyId() != null) {

            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Long dateCount = Integer.toUnsignedLong(7);

            // --------------------------------------
            if (chartDataRequestDto.getStartDate() != null && chartDataRequestDto.getEndDate() != null) {
                Date startDate = chartDataRequestDto.getStartDate();
                Date endDate = chartDataRequestDto.getEndDate();
                Long difference = startDate.getTime() - endDate.getTime();
                dateCount = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                calendar.setTime(startDate);
            } else {
                calendar.setTime(new Date());
            }

            calendar.add(Calendar.DATE, -Math.toIntExact(dateCount));

            // --------------------------------------

            ChartResponseDto chartResponseDto = new ChartResponseDto();

            Company company = new Company();
            company.setId(chartDataRequestDto.getCompanyId());
            List<SalesStaticDataSto> salesStaticDataDto = salesRepository.loadSalesStaticData(company);

            List<Object> keyList = new ArrayList<>();
            List<Object> valueList = new ArrayList<>();

            for (int x=1; x <= Math.toIntExact(dateCount); x++) {
                calendar.add(Calendar.DATE, 1);
                SalesStaticDataSto salesStaticDataSto = salesStaticDataDto.stream().filter(date -> date.getSaleDate().equals(simpleDateFormat.format(calendar.getTime()))).findAny().orElse(null);

                if (salesStaticDataSto != null ) {
                    keyList.add(salesStaticDataSto.getSaleDate());
                    valueList.add(salesStaticDataSto.getTotalAmount());
                } else {
                    keyList.add(simpleDateFormat.format(calendar.getTime()));
                    valueList.add(0.00);
                }
            }

            chartResponseDto.setKeyList(keyList);
            chartResponseDto.setValueList(valueList);

            responseDto.setResponseObject(chartResponseDto);
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Invalid Request");
        }

        return responseDto;
    }
}
