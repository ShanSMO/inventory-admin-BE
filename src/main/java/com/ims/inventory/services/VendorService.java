package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Vendor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VendorService {

    ResponseDto createUpdate(Vendor vendor);
    ResponseDto search(Vendor vendor);
    ResponseDto loadAll(Vendor vendor);
    ResponseDto loadById(Vendor vendor);
    ResponseDto loadByPhone(Vendor vendor);
    ResponseDto checkVendorExists(Vendor vendor);
    ResponseDto loadAllCount(Vendor vendor);
    ResponseDto addToBlackList(Vendor vendor);
    ResponseDto remove(Vendor vendor);
}
