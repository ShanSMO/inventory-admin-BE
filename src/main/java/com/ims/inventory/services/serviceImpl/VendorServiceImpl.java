package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.enums.ActiveStatus;
import com.ims.inventory.models.PurchaseOrder;
import com.ims.inventory.models.QPurchaseOrder;
import com.ims.inventory.models.QVendor;
import com.ims.inventory.models.Vendor;
import com.ims.inventory.repositories.PurchaseOrderRepository;
import com.ims.inventory.repositories.VendorRepository;
import com.ims.inventory.services.VendorService;
import com.ims.inventory.utils.CodeGenerator;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    QVendor qVendor = QVendor.vendor;

    @Override
    public ResponseDto createUpdate(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();
        if (vendor.getId() == null) {
            vendor.setStatus(ActiveStatus.ACTIVE);
        }
        Vendor savedVendor = vendorRepository.saveAndFlush(vendor);
        savedVendor.setVendorCode(CodeGenerator.generateCode(Consts.GEN_TYPE_SUPPLIER, savedVendor.getId()));
        responseDto.setResponseObject(savedVendor);
        return responseDto;
    }

    @Override
    public ResponseDto search(Vendor vendor) {
        return null;
    }

    @Override
    public ResponseDto loadAll(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();

        if (vendor.getCompany() != null && vendor.getCompany().getId() != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            QVendor qVendor = QVendor.vendor;
            booleanBuilder.and(qVendor.company.id.eq(vendor.getCompany().getId()));
            responseDto.setResponseItems(Lists.newArrayList(vendorRepository.findAll(booleanBuilder)));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_SUCCESS);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadById(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseObject(vendorRepository.findById(vendor.getId()));
        return responseDto;
    }

    @Override
    public ResponseDto loadAllCount(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();
        if (vendor.getCompany() != null && vendor.getCompany().getId() != null) {
            QVendor qVendor = QVendor.vendor;
            BooleanBuilder booleanBuilder = new BooleanBuilder(qVendor.company.id.eq(vendor.getCompany().getId()));
            responseDto.setResponseObject(vendorRepository.count(booleanBuilder));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    @Override
    public ResponseDto addToBlackList(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendor.getId());
        Vendor loadedVendor = vendorOptional.orElse(null);
        loadedVendor.setStatus(ActiveStatus.BLOCKED);
        vendorRepository.save(loadedVendor);
        return responseDto;
    }

    @Override
    public ResponseDto loadByPhone(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Optional<Vendor> vendorOptional = vendorRepository.findOne(booleanBuilder.and(qVendor.phoneNumber.eq(vendor.getPhoneNumber())));
        if (vendorOptional.isPresent()) {
            responseDto.setResponseObject(vendorOptional.get());
        } else {
            responseDto.setResponseObject(null);
        }
        return responseDto;
    }

    @Override
    public ResponseDto checkVendorExists(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder = booleanBuilder.or(qVendor.phoneNumber.eq(vendor.getPhoneNumber()))
                .or(qVendor.firstName.eq(vendor.getFirstName()))
                .or(qVendor.emailAddress.eq(vendor.getEmailAddress()));
        Optional<Vendor> vendorOptional = vendorRepository.findOne(booleanBuilder);

        if (vendorOptional.isPresent()) {
            responseDto.setResponseObject(vendorOptional.get());
        } else {
            responseDto.setResponseObject(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto remove(Vendor vendor) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QVendor qVendor = QVendor.vendor;

        if (vendor.getCompany() !=null && vendor.getCompany().getId() != null && vendor.getId() != null) {

            booleanBuilder.and(qVendor.company.id.eq(vendor.getCompany().getId()));
            booleanBuilder.and(qVendor.id.eq(vendor.getId()));

            if (vendorRepository.findOne(booleanBuilder).isPresent()) {

                QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
                BooleanBuilder booleanBuilder1 = new BooleanBuilder();
                booleanBuilder1.and(qPurchaseOrder.vendor.id.eq(vendor.getId()));
                List<PurchaseOrder> purchaseOrders = Lists.newArrayList(purchaseOrderRepository.findAll(booleanBuilder1));
                if (purchaseOrders.size() <= 0) {
                    vendorRepository.deleteById(vendor.getId());
                    responseDto.setStatus(Consts.JOB_SUCCESS);
                } else {
                    responseDto.setStatus(Consts.JOB_FAILED);
                    responseDto.setMessage("Supplier already in use");
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
