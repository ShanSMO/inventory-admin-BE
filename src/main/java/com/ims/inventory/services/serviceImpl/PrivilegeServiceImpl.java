package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.CustomDtos.PrivilegeDto;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.PrivilegeType;
import com.ims.inventory.models.QPrivilegeType;
import com.ims.inventory.models.User;
import com.ims.inventory.repositories.PrivilegeRepository;
import com.ims.inventory.repositories.UserRepository;
import com.ims.inventory.services.PrivilegeService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PrivilegeServiceImpl implements PrivilegeService{

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    UserRepository userRepository;

    QPrivilegeType qPrivilegeType = QPrivilegeType.privilegeType1;

    @Override
    public ResponseDto loadAll(PrivilegeType privilegeType) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        User user = null;

        if (privilegeType.getParentId() != null && privilegeType.getParentId() > 0) {
            booleanBuilder.and(qPrivilegeType.parentId.eq(privilegeType.getParentId()));
        } else {
            booleanBuilder.and(qPrivilegeType.parentId.eq(0L));
        }

        if (privilegeType.getRelatedCompany() != null && privilegeType.getRelatedCompany().getId() != null) {
            booleanBuilder.and(qPrivilegeType.relatedCompany.id.eq(privilegeType.getRelatedCompany().getId()));
        }

        List<PrivilegeType> privilegeTypes = Lists.newArrayList(privilegeRepository.findAll(booleanBuilder));
        if (privilegeType.getUserId() != null) {
            Optional<User> userOpt = userRepository.findById(privilegeType.getUserId());
            user = userOpt.orElse(null);
        }
        List<PrivilegeDto> privilegeDtoList = new ArrayList<>();

        if (user != null) {
            for (PrivilegeType privilege : privilegeTypes) {
                PrivilegeDto privilegeDto = new PrivilegeDto();
                privilegeDto.setId(privilege.getId());
                privilegeDto.setPrivilege(privilege);
                privilegeDto.setHasAccess(user.getPrivilegeTypes().contains(privilege));
                privilegeDtoList.add(privilegeDto);
            }
        } else {
            for (PrivilegeType privilege : privilegeTypes) {
                PrivilegeDto privilegeDto = new PrivilegeDto();
                privilegeDto.setId(privilege.getId());
                privilegeDto.setPrivilege(privilege);
                privilegeDto.setHasAccess(false);
                privilegeDtoList.add(privilegeDto);
            }
        }


        responseDto.setResponseItems(privilegeDtoList);
        return responseDto;
    }

    @Override
    public ResponseDto assignPrivileges(PrivilegeType privilegeType) {
        ResponseDto responseDto = new ResponseDto();

        if (privilegeType.getUserId() != null) {
            Optional<User> userOpt = userRepository.findById(privilegeType.getUserId());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                List<PrivilegeType> privilegeTypes =  user.getPrivilegeTypes();

                if (privilegeType.getAssignType().equals("ADD")) {
                    privilegeTypes.add(privilegeType);
                } else {
                    privilegeTypes.remove(privilegeTypes.stream().filter(prvl -> Objects.equals(prvl.getId(), privilegeType.getId())).findFirst().get());
                }

                user.setPrivilegeTypes(privilegeTypes);
                userRepository.saveAndFlush(user);
            }
        }

        return responseDto;
    }
}
