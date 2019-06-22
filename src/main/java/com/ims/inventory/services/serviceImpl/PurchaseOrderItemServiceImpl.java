package com.ims.inventory.services.serviceImpl;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PurchaseOrderItem;
import com.ims.inventory.repositories.PurchaseOrderItemRepository;
import com.ims.inventory.services.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {

    @Autowired
    PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Override
    public ResponseDto loadItem(PurchaseOrderItem purchaseOrderItem) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseObject(purchaseOrderItemRepository.findById(purchaseOrderItem.getId()));
        return responseDto;
    }

}
