package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.*;
import com.ims.inventory.repositories.CustomerRepository;
import com.ims.inventory.repositories.RecentActivityRepository;
import com.ims.inventory.repositories.SalesRepository;
import com.ims.inventory.services.SalesService;
import com.ims.inventory.services.StockService;
import com.ims.inventory.utils.CodeGenerator;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    RecentActivityRepository recentActivityRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StockService stockService;

    @Override
    public ResponseDto createOrUpdate(Sales sales) {
        ResponseDto responseDto = new ResponseDto();
        Sales savedSSale = salesRepository.saveAndFlush(sales);
        String orderId = CodeGenerator.generateCode(Consts.GEN_TYPE_SALES, savedSSale.getId());
        savedSSale.setOrderId(orderId);
        savedSSale = salesRepository.saveAndFlush(savedSSale);
        responseDto.setResponseObject(savedSSale);

        if(sales.getId() != null) {

            for(SalesOrderItem purchaseOrderItem : sales.getSalesOrderItems()) {
                Stock stock = new Stock();
                stock.setProduct(purchaseOrderItem.getProduct());
                stock = (Stock) stockService.finByProductId(stock).getResponseObject();

                if(stock != null) {
                    int newStock = stock.getCount() - purchaseOrderItem.getQuantity();
                    stock.setCount(newStock);
                    stockService.addUpdateStockItem(stock);
                }
            }

            RecentActivity recentActivity = new RecentActivity();
            recentActivity.setCompany(sales.getCompany());
            recentActivity.setCreatedDate(new Date());
            Optional<Customer> customerOptional = customerRepository.findById(sales.getCustomer().getId());
            String customerName = null;
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                customerName = customer.getFirstName();
            }
            recentActivity.setActivityName("Sale | Value : ".concat(sales.getOrderAmount().toString()).concat(" | To : ").concat(customerName));
            recentActivityRepository.saveAndFlush(recentActivity);
        }


        return responseDto;
    }

    @Override
    public ResponseDto loadAll(Sales sales) {
        ResponseDto responseDto = new ResponseDto();

        if (sales.getCompany() != null && sales.getCompany().getId() != null) {
            QSales qSales = QSales.sales;
            BooleanBuilder booleanBuilder = new BooleanBuilder(qSales.company.id.eq(sales.getCompany().getId()));
            responseDto.setResponseItems(Lists.newArrayList(salesRepository.findAll(booleanBuilder)));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadById(Sales sales) {
        ResponseDto responseDto = new ResponseDto();

        if (sales.getId() != null) {
            Optional<Sales> salesOptional = salesRepository.findById(sales.getId());
            salesOptional.ifPresent(responseDto::setResponseObject);
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }
        return responseDto;
    }
}
