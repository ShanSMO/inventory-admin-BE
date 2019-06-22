package com.ims.inventory.services;

import com.ims.inventory.dtos.OauthResponse;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.SuperAdmin;
import com.ims.inventory.models.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    ResponseDto createOrUpdate(User user);
    ResponseDto loadById(User user);
    ResponseDto login(User user);
    ResponseDto loadByUserName(User user);
    ResponseDto loadByFirstName(User user);
    ResponseDto loadAllByCompanyId(User user);

    ResponseDto createOrUpdateSuperAdmin(SuperAdmin superAdmin);
    ResponseDto loadSuperAdmins(SuperAdmin superAdmin);
    OauthResponse loginSuperAdmin(SuperAdmin superAdmin);

}
