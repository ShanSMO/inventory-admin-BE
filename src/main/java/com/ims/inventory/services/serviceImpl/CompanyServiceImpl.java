package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.CustomDtos.CompanyCustomDto;
import com.ims.inventory.dtos.ResourceFile;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.enums.ActiveStatus;
import com.ims.inventory.models.*;
import com.ims.inventory.repositories.*;
import com.ims.inventory.services.CompanyService;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

@Service
@PropertySource("classpath:common.properties")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DefaultPrivilegeRepository defaultPrivilegeRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    SoftwareSystemSettingRepository softwareSystemSettingRepository;

    @Autowired
    private Environment env;

    QCompany qCompany = QCompany.company;

    @Override
    public ResponseDto create(CompanyCustomDto companyDto) {
        ResponseDto responseDto = new ResponseDto();

        if (!isCompanyUserExists(companyDto)) {
            if (companyDto.getUserName() != null && companyDto.getUserPassword() != null && companyDto.getContactNumber() != null) {
                Company company = new Company();
                if (companyDto.getId() != null) {
                    company.setId(companyDto.getId());
                }

                if (companyDto.getResourceFile() != null && companyDto.getResourceFile().getBase64String() != null) {
                    try {
                        resourceFileSave(companyDto.getResourceFile());
                        company.setCompanyLogo(env.getProperty("file.company-logo-path-public") + companyDto.getResourceFile().getFileName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    company.setCompanyLogo(companyDto.getCompanyLogo());
                }

                //----- Setting the expiration date
                List<SoftwareSystemSettings> softwareSystemSettings = softwareSystemSettingRepository.findAll();
                SoftwareSystemSettings softwareSystemSettings1 = softwareSystemSettings.stream().filter(setting ->
                        setting.getSettingKey().equals("TRIAL_PERIOD")).findAny().orElse(null);
                if (softwareSystemSettings1 != null) {
                    Date date = new Date();
                    Integer trialPeriod = Integer.parseInt(softwareSystemSettings1.getSettingValue());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, trialPeriod);
                    company.setExpireDate(calendar.getTime());
                }

                company.setCompanyName(companyDto.getCompanyName());
                company.setRegisteredDate(new Date());
                company.setContactNumber(companyDto.getContactNumber());
                company.setOwner(companyDto.getOwner());
                company = companyRepository.saveAndFlush(company);

                User user = createAdminUser(company, companyDto);
                createPrivileges(company, user);

                responseDto.setStatus(Consts.JOB_SUCCESS);
            } else {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Invalid details");
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Please use a different user name");
        }


        return responseDto;
    }

    @Override
    public ResponseDto update(CompanyCustomDto companyDto) {
        ResponseDto responseDto = new ResponseDto();

        if (companyDto.getCompanyName() != null && companyDto.getContactNumber() != null && companyDto.getOwner() != null) {
            Company company = new Company();
            if (companyDto.getId() != null) {
                company.setId(companyDto.getId());
            }

            if (companyDto.getResourceFile() != null && companyDto.getResourceFile().getBase64String() != null) {
                try {
                    resourceFileSave(companyDto.getResourceFile());
                    company.setCompanyLogo(env.getProperty("file.company-logo-path-public") + companyDto.getResourceFile().getFileName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                company.setCompanyLogo(companyDto.getCompanyLogo());
            }

            company.setCompanyName(companyDto.getCompanyName());
            company.setContactNumber(companyDto.getContactNumber());
            company.setOwner(companyDto.getOwner());
            companyRepository.saveAndFlush(company);

            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Invalid details");
        }

        return responseDto;
    }

    public void resourceFileSave(ResourceFile resourceFile) throws Exception{
        String filePath = env.getProperty("file.company-logo-path") + resourceFile.getFileName();
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] resFile = base64Decoder.decodeBuffer(resourceFile.getBase64String());
        OutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(resFile);
    }

    @Override
    public ResponseDto loadAll() {
        ResponseDto responseDto = new ResponseDto();
        List<Company> companyList = companyRepository.findAll();
        responseDto.setStatus(Consts.JOB_SUCCESS);
        responseDto.setResponseItems(companyList);
        return responseDto;
    }

    @Override
    public ResponseDto loadById(CompanyCustomDto company) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (company != null && company.getId() != null) {
            Optional<Company> companyOptional = companyRepository.findOne(booleanBuilder.and(qCompany.id.eq(company.getId())));
            if (companyOptional.isPresent()) {
                responseDto.setResponseObject(companyOptional.get());
                responseDto.setStatus(Consts.JOB_SUCCESS);
            } else {
                responseDto.setResponseObject(null);
                responseDto.setStatus(Consts.JOB_SUCCESS);
                responseDto.setMessage("No record found");
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    private void createPrivileges(Company company, User user) {
        List<DefaultPrivileges> privilegeList = defaultPrivilegeRepository.findAll();
        List<PrivilegeType> privilegeTypes = new ArrayList<>();
        for (DefaultPrivileges defaultPrivilege: privilegeList) {
            PrivilegeType privilegeType = new PrivilegeType();
            privilegeType.setIcon(defaultPrivilege.getIcon());
            privilegeType.setLevel(defaultPrivilege.getLevel());
            privilegeType.setLevelParent(defaultPrivilege.getLevelParent());
            privilegeType.setParentId(defaultPrivilege.getParentId());
            privilegeType.setPrivilegeName(defaultPrivilege.getPrivilegeName());
            privilegeType.setPrivilegeType(defaultPrivilege.getPrivilegeType());
            privilegeType.setRelatedCompany(company);
            privilegeType.setUrl(defaultPrivilege.getUrl());
            privilegeType.setPrvId(defaultPrivilege.getPrvId());
            privilegeTypes.add(privilegeType);
            privilegeRepository.saveAndFlush(privilegeType);
        }

        user.setPrivilegeTypes(privilegeTypes);
        userRepository.saveAndFlush(user);
    }

    private User createAdminUser(Company company, CompanyCustomDto companyDto) {
        User user = new User();
        user.setCompany(company);
        user.setDisplayName(Consts.DEFAULT_MAIN_USER);
        user.setStatus(ActiveStatus.PENDING);
        user.setUserName(companyDto.getUserName());
        user.setUserPassword(companyDto.getUserPassword());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public ResponseDto loadAllCount(CompanyCustomDto company) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseObject(companyRepository.count());
        QCompany qCompany = QCompany.company;

        HashMap<String,Long> companyTypeCount = new HashMap<>();
        companyTypeCount.put("ALL", companyRepository.count());
        companyTypeCount.put(ActiveStatus.ACTIVE.toString(), companyRepository.count(new BooleanBuilder(qCompany.status.eq(ActiveStatus.ACTIVE))));
        companyTypeCount.put(ActiveStatus.BLOCKED.toString(), companyRepository.count(new BooleanBuilder(qCompany.status.eq(ActiveStatus.BLOCKED))));
        companyTypeCount.put(ActiveStatus.PENDING.toString(), companyRepository.count(new BooleanBuilder(qCompany.status.eq(ActiveStatus.PENDING))));
        companyTypeCount.put(ActiveStatus.EXPIRED.toString(), companyRepository.count(new BooleanBuilder(qCompany.status.eq(ActiveStatus.EXPIRED))));
        companyTypeCount.put(ActiveStatus.INACTIVE.toString(), companyRepository.count(new BooleanBuilder(qCompany.status.eq(ActiveStatus.INACTIVE))));
        responseDto.setResponseObject(companyTypeCount);
        return responseDto;
    }

    @Override
    public ResponseDto loadCompanyByStatus(CompanyCustomDto company) {
        ResponseDto responseDto = new ResponseDto();
        QCompany qCompany = QCompany.company;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qCompany.status.eq(company.getStatus()));
        List<Company> companyList = Lists.newArrayList(companyRepository.findAll(booleanBuilder));
        responseDto.setResponseItems(companyList);
        return responseDto;
    }

    @Override
    public ResponseDto changeStatus(CompanyCustomDto company) {
        ResponseDto responseDto = new ResponseDto();

        Optional<Company> companyOptional = companyRepository.findById(company.getId());
        if (companyOptional.isPresent()) {
            Company companyForUpdate = companyOptional.get();
            companyForUpdate.setId(company.getId());
            companyForUpdate.setStatus(company.getStatus());

            if (company.getStatus().equals(ActiveStatus.ACTIVE)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DATE, 30);
                companyForUpdate.setExpireDate(calendar.getTime());
            }
            companyRepository.save(companyForUpdate);
        }

        return responseDto;
    }

    private Boolean isCompanyUserExists(CompanyCustomDto companyCustomDto) {
        QUser qUser = QUser.user;
        if (companyCustomDto != null && companyCustomDto.getUserName() != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder(qUser.userName.eq(companyCustomDto.getUserName()));
            Optional<User> userOptional = userRepository.findOne(booleanBuilder);
            return userOptional.isPresent();
        } else {
            return false;
        }

    }
}
