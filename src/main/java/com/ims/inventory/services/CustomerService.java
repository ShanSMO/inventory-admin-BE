package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Customer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerService {

    ResponseDto create(Customer customer);
    ResponseDto search(Customer customer);
    ResponseDto loadAll(Customer customer);
    ResponseDto loadById(Customer customer);
    ResponseDto getCount(Customer customer);
    ResponseDto isExists(Customer customer);
    ResponseDto remove(Customer customer);
}
