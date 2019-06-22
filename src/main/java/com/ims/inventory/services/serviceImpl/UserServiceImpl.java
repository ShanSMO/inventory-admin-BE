package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.ims.inventory.dtos.OauthResponse;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.enums.ActiveStatus;
import com.ims.inventory.models.*;
import com.ims.inventory.repositories.SuperAdminRepository;
import com.ims.inventory.repositories.UserRepository;
import com.ims.inventory.services.UserService;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    SuperAdminRepository superAdminRepository;

    @Autowired
    private Environment environment;

    @Override
    public ResponseDto createOrUpdate(User user) {
        ResponseDto responseDto = new ResponseDto();
        if (user.getId() == null) {
            user.setStatus(ActiveStatus.ACTIVE);
        }
        responseDto.setResponseObject(userRepository.saveAndFlush(user));
        return responseDto;
    }

    @Override
    public ResponseDto loadById(User user) {
        ResponseDto responseDto = new ResponseDto();
        List<User> userList = userRepository.findAll();
        responseDto.setResponseItems(userList);
        return responseDto;
    }

    @Override
    public ResponseDto login(User user) {
        ResponseDto responseDto = new ResponseDto();
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (user != null && user.getUserName() != null && user.getUserPassword() != null) {
            booleanBuilder.and(qUser.userName.eq(user.getUserName()));
            booleanBuilder.and(qUser.userPassword.eq(user.getUserPassword()));

            Optional<User> userOptional = userRepository.findOne(booleanBuilder);
            if (userOptional.isPresent()) {
                if (userOptional.get().getStatus().equals(ActiveStatus.ACTIVE))  {
                    User loggedUser = new User();
                    loggedUser.setUserName(userOptional.get().getUserName());
                    loggedUser.setDisplayName(userOptional.get().getDisplayName());
                    loggedUser.setPrivilegeTypes(userOptional.get().getPrivilegeTypes());
                    Company company = new Company();
                    company.setCompanyName(userOptional.get().getCompany().getCompanyName());
                    company.setId(userOptional.get().getCompany().getId());
                    company.setCompanyLogo(userOptional.get().getCompany().getCompanyLogo());
                    loggedUser.setCompany(company);
                    responseDto.setResponseObject(loggedUser);
                    responseDto.setStatus("SUCCESS");
                    responseDto.setMessage("Login Success");
                } else if (userOptional.get().getStatus().equals(ActiveStatus.INACTIVE)) {
                    responseDto.setStatus("FAILED");
                    responseDto.setMessage("Account is Inactive, Please activate.");
                } else if (userOptional.get().getStatus().equals(ActiveStatus.BLOCKED)) {
                    responseDto.setStatus("FAILED");
                    responseDto.setMessage("Login failed , Contact us !");
                } else if (userOptional.get().getStatus().equals(ActiveStatus.PENDING)) {
                    responseDto.setStatus("FAILED");
                    responseDto.setMessage("Your account is under reviewing , please wait for approve the details.");
                } else {
                    responseDto.setStatus("FAILED");
                    responseDto.setMessage("Server error");
                }

            } else {
                booleanBuilder = new BooleanBuilder();
                booleanBuilder.and(qUser.userName.eq(user.getUserName()));
                Optional<User> userCheck = userRepository.findOne(booleanBuilder);
                if (!userCheck.isPresent()) {
                    responseDto.setStatus("FAILED");
                    responseDto.setMessage("User not found, please register with us");
                } else {
                    responseDto.setStatus("FAILED");
                    responseDto.setMessage("Invalid credentials");
                }
            }
        } else {
            responseDto.setStatus("FAILED");
            responseDto.setMessage("Invalid input");
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadByUserName(User user) {
        return null;
    }

    @Override
    public ResponseDto loadByFirstName(User user) {
        return null;
    }

    @Override
    public ResponseDto loadAllByCompanyId(User user) {
        ResponseDto responseDto = new ResponseDto();

        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (user.getCompany() != null && user.getCompany().getId() != null) {
            booleanBuilder.and(qUser.company.id.eq(user.getCompany().getId()));
        }

        responseDto.setResponseItems(Lists.newArrayList(userRepository.findAll(booleanBuilder)));
        return responseDto;
    }

    @Override
    public ResponseDto createOrUpdateSuperAdmin(SuperAdmin superAdmin) {
        ResponseDto responseDto = new ResponseDto();
        if (superAdmin.getId() == null) {
            superAdmin.setStatus(ActiveStatus.ACTIVE);
        }
        responseDto.setResponseObject(superAdminRepository.saveAndFlush(superAdmin));
        return responseDto;
    }

    @Override
    public ResponseDto loadSuperAdmins(SuperAdmin superAdmin) {
        return null;
    }

    @Override
    public OauthResponse loginSuperAdmin(SuperAdmin superAdmin) {

        OauthResponse oauthResponse = new OauthResponse();
        ResponseDto responseDto = new ResponseDto();

        if (superAdmin.getUserName() != null && superAdmin.getPassword() != null) {

            try {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Authorization","Basic ZS1iaXo6MTIzNA==");
                HttpEntity<String> headersHttpEntity =  new HttpEntity<String>(httpHeaders);
                String body = restTemplate.exchange(environment.getProperty("auth-server.url")+"grant_type=password&username="+superAdmin.getUserName()+"&password="+superAdmin.getPassword(), HttpMethod.POST, headersHttpEntity,String.class).getBody();
                Gson gson = new Gson();
                oauthResponse = gson.fromJson(body, OauthResponse.class);

                if (body != null) {
                    responseDto.setStatus(Consts.JOB_SUCCESS);
                } else {
                    responseDto.setStatus(Consts.JOB_FAILED);
                }
            } catch (Exception e) {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Invalid request, Try Again");
            }


        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }
        return oauthResponse;
    }

}
