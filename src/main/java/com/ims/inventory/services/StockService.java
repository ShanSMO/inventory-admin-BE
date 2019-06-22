package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Stock;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StockService {

    ResponseDto addUpdateStockItem(Stock stock);
    ResponseDto finByProductId(Stock stock);
    ResponseDto loadStock();
    ResponseDto loadStockForList(Stock stock);
    ResponseDto loadByBarcode(Stock stock);
    ResponseDto loadByProduct(Stock stock);
}
