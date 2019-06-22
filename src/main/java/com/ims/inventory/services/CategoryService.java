package com.ims.inventory.services;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Category;
import com.ims.inventory.models.SubCategory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryService {

    ResponseDto createUpdate(Category category);
    ResponseDto createUpdateSub(SubCategory category);
    ResponseDto search(Category category);
    ResponseDto loadAll(Category category);
    ResponseDto loadAllSub(SubCategory category);
    ResponseDto loadById(Category category);
    ResponseDto removeCategory(Category category);
    ResponseDto removeSubCategory(SubCategory category);

}
