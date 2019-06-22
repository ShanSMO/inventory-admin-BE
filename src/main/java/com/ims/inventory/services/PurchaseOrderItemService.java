package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PurchaseOrderItem;

import javax.transaction.Transactional;

@Transactional
public interface PurchaseOrderItemService {

    ResponseDto loadItem(PurchaseOrderItem purchaseOrderItem);
}
