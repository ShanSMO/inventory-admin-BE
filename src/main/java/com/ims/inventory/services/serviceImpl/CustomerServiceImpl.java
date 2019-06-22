package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.enums.ActiveStatus;
import com.ims.inventory.models.Customer;
import com.ims.inventory.models.QCustomer;
import com.ims.inventory.models.QSales;
import com.ims.inventory.models.Sales;
import com.ims.inventory.repositories.CustomerRepository;
import com.ims.inventory.repositories.SalesRepository;
import com.ims.inventory.services.CustomerService;
import com.ims.inventory.utils.CodeGenerator;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SalesRepository salesRepository ;

    @Override
    public ResponseDto create(Customer customer) {
        ResponseDto responseDto = new ResponseDto();
        if (customer.getId() == null) {
            customer.setStatus(ActiveStatus.ACTIVE);
        }
        Customer savedCustomer = customerRepository.saveAndFlush(customer);
        savedCustomer.setCustomerId(CodeGenerator.generateCode(Consts.GEN_TYPE_CUSTOMER ,savedCustomer.getId()));
        responseDto.setResponseObject(savedCustomer);
        return responseDto;
    }

    @Override
    public ResponseDto search(Customer customer) {
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }

    @Override
    public ResponseDto loadAll(Customer customer) {
        ResponseDto responseDto = new ResponseDto();

        if (customer.getCompany() != null && customer.getCompany().getId()  != null) {
            QCustomer qCustomer = QCustomer.customer;
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.and(qCustomer.company.id.eq(customer.getCompany().getId()));
            responseDto.setResponseItems(Lists.newArrayList(customerRepository.findAll(booleanBuilder)));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadById(Customer customer) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseObject(customerRepository.findById(customer.getId()));
        return responseDto;
    }

    @Override
    public ResponseDto getCount(Customer customer) {
        ResponseDto responseDto = new ResponseDto();
        if (customer.getCompany() != null && customer.getCompany().getId() != null){
            QCustomer qCustomer = QCustomer.customer;
            BooleanBuilder booleanBuilder = new BooleanBuilder(qCustomer.company.id.eq(customer.getCompany().getId()));
            responseDto.setResponseObject(customerRepository.count(booleanBuilder));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }
        return responseDto;
    }

    @Override
    public ResponseDto isExists(Customer customer) {
        return null;
    }

    @Override
    public ResponseDto remove(Customer customer) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QCustomer qCustomer = QCustomer.customer;

        if (customer.getCompany() !=null && customer.getCompany().getId() != null && customer.getId() != null) {

            booleanBuilder.and(qCustomer.company.id.eq(customer.getCompany().getId()));
            booleanBuilder.and(qCustomer.id.eq(customer.getId()));

            if (customerRepository.findOne(booleanBuilder).isPresent()) {

                QSales qSales = QSales.sales;
                BooleanBuilder booleanBuilder1 = new BooleanBuilder();
                booleanBuilder1.and(qSales.customer.id.eq(customer.getId()));
                List<Sales> salesList = Lists.newArrayList(salesRepository.findAll(booleanBuilder1));
                if (salesList.size() <= 0) {
                    customerRepository.deleteById(customer.getId());
                    responseDto.setStatus(Consts.JOB_SUCCESS);
                } else {
                    responseDto.setStatus(Consts.JOB_FAILED);
                    responseDto.setMessage("Customer already in use");
                }
            } else {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Invalid Request");
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Invalid Request");
        }

        return responseDto;
    }
}
