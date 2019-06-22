package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PurchaseOrder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PurchaseOrderService {

    ResponseDto createOrUpdate(PurchaseOrder purchaseOrder);
    ResponseDto loadAll(PurchaseOrder purchaseOrder);

}
