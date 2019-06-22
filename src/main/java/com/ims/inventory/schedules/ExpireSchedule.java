package com.ims.inventory.schedules;

import com.google.common.collect.Lists;
import com.ims.inventory.enums.ActiveStatus;
import com.ims.inventory.models.Company;
import com.ims.inventory.models.QCompany;
import com.ims.inventory.repositories.CompanyRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class ExpireSchedule {

    @Autowired
    private CompanyRepository companyRepository;

    @Scheduled(cron = "*/60 * * * * *")
    public void companyExpire() {

        QCompany qCompany = QCompany.company;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qCompany.expireDate.lt(new Date()));
        booleanBuilder.and(qCompany.status.eq(ActiveStatus.ACTIVE));
        List<Company> companyList = Lists.newArrayList(companyRepository.findAll(booleanBuilder));
        System.out.println(companyList.size());
        for(Company company : companyList) {
            company.setStatus(ActiveStatus.EXPIRED);
            companyRepository.save(company);
        }
    }

}
