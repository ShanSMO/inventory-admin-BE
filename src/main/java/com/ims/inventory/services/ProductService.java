package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Product;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService {

    ResponseDto createUpdate(Product product);
    ResponseDto block(Product product);
    ResponseDto search(Product product);
    ResponseDto loadAll(Product product);
    ResponseDto loadById(Product product);
    ResponseDto loadProductsForCategory(Product product);
    ResponseDto loadAllGroupByCategory();
    ResponseDto loadByBarcode(Product product);
    ResponseDto remove(Product product);
}
