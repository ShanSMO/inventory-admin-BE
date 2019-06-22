package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.QRecentActivity;
import com.ims.inventory.models.RecentActivity;
import com.ims.inventory.repositories.RecentActivityRepository;
import com.ims.inventory.services.RecentActivityService;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class RecentActivityServiceImpl implements RecentActivityService {

    @Autowired
    RecentActivityRepository recentActivityRepository;

    @Override
    public ResponseDto loadAll(RecentActivity recentActivity) {
        ResponseDto responseDto = new ResponseDto();

        if (recentActivity.getCompany() != null && recentActivity.getCompany().getId() != null) {
            QRecentActivity qRecentActivity = QRecentActivity.recentActivity;
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.and(qRecentActivity.company.id.eq(recentActivity.getCompany().getId()));
            responseDto.setResponseItems(Lists.newArrayList(recentActivityRepository.findRecentTasks(recentActivity.getCompany().getId(), new PageRequest(0,10))));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }
}
