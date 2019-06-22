package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.RecentActivity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RecentActivityService {

    ResponseDto loadAll(RecentActivity recentActivity);

}
