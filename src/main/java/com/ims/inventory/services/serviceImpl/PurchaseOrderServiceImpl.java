package com.ims.inventory.services.serviceImpl;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.*;
import com.ims.inventory.repositories.PurchaseOrderRepository;
import com.ims.inventory.repositories.RecentActivityRepository;
import com.ims.inventory.repositories.StockRepository;
import com.ims.inventory.repositories.VendorRepository;
import com.ims.inventory.services.PurchaseOrderService;
import com.ims.inventory.services.StockService;
import com.ims.inventory.utils.CodeGenerator;
import com.ims.inventory.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    RecentActivityRepository recentActivityRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    StockService stockService;

    @Override
    public ResponseDto createOrUpdate(PurchaseOrder purchaseOrder) {
        ResponseDto responseDto = new ResponseDto();

        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.saveAndFlush(purchaseOrder);
        savedPurchaseOrder.setPurchaseOrderId(CodeGenerator.generateCode(Consts.GEN_TYPE_PO, savedPurchaseOrder.getId()));
        responseDto.setResponseObject(savedPurchaseOrder);

        if(purchaseOrder.getId() != null) {

            for(PurchaseOrderItem purchaseOrderItem : purchaseOrder.getPurchaseOrderItems()) {
                Stock stock = new Stock();
                stock.setProduct(purchaseOrderItem.getProduct());
                stock = (Stock) stockService.finByProductId(stock).getResponseObject();

                if(stock != null) {
                    int newStock = stock.getCount() + purchaseOrderItem.getQuantity();
                    stock.setCount(newStock);
                    stockService.addUpdateStockItem(stock);
                }
            }

            RecentActivity recentActivity = new RecentActivity();
            recentActivity.setCompany(purchaseOrder.getCompany());
            recentActivity.setCreatedDate(new Date());

            String supplierName = null;
            Optional<Vendor> vendorOptional = vendorRepository.findById(purchaseOrder.getVendor().getId());
            if (vendorOptional.isPresent()) {
                supplierName = vendorOptional.get().getFirstName();
            }

            recentActivity.setActivityName("PO | Value : ".concat(purchaseOrder.getTotalAmount().toString()).concat(" | From : ").concat(supplierName));
            recentActivityRepository.saveAndFlush(recentActivity);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadAll(PurchaseOrder purchaseOrder) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseItems(purchaseOrderRepository.findAll());
        return responseDto;
    }
}
