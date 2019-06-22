package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PrivilegeType;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PrivilegeService {

    ResponseDto loadAll(PrivilegeType privilegeType);
    ResponseDto assignPrivileges(PrivilegeType privilegeType);
}
