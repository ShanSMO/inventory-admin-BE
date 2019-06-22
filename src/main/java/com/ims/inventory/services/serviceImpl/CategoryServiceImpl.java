package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.CustomDtos.CategoryCustomDto;
import com.ims.inventory.dtos.ResourceFile;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.*;
import com.ims.inventory.repositories.CategoryRepository;
import com.ims.inventory.repositories.ProductRepository;
import com.ims.inventory.repositories.SubCategoryRepository;
import com.ims.inventory.services.CategoryService;
import com.ims.inventory.utils.CodeGenerator;
import com.ims.inventory.utils.Consts;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:common.properties")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private Environment env;


    @Override
    public ResponseDto createUpdate(Category category) {
        ResponseDto responseDto = new ResponseDto();

        if (!isExists(category.getCategoryName(), "CATEGORY")) {
            try {
                resourceFileSave(category.getResourceFile());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (category.getResourceFile() != null) {
                category.setCategoryImage(env.getProperty("file.category-image-path-public") + category.getResourceFile().getFileName());
            }

            Category savedCategory = categoryRepository.saveAndFlush(category);
            savedCategory.setCategoryCode(CodeGenerator.generateCode(Consts.GEN_TYPE_CATEGORY, savedCategory.getId()));
            responseDto.setResponseObject(savedCategory);
            responseDto.setStatus("SUCCESS");
        } else {
            responseDto.setStatus("FAILED");
        }

        return responseDto;
    }

    @Override
    public ResponseDto createUpdateSub(SubCategory category) {
        ResponseDto responseDto = new ResponseDto();

        if (!isExists(category.getCategoryName(), "SUBCATEGORY")) {
            try {
                resourceFileSave(category.getResourceFile());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (category.getResourceFile() != null) {
                category.setCategoryImage(env.getProperty("file.category-image-path-public") + category.getResourceFile().getFileName());
            }

            SubCategory savedCategory = subCategoryRepository.saveAndFlush(category);
            savedCategory.setCategoryCode(CodeGenerator.generateCode(Consts.GEN_TYPE_CATEGORY, savedCategory.getId()));
            responseDto.setResponseObject(savedCategory);
            responseDto.setStatus("SUCCESS");
        } else {
            responseDto.setStatus("FAILED");
        }
        return responseDto;
    }

    @Override
    public ResponseDto search(Category category) {
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }

    @Override
    public ResponseDto loadAll(Category category) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QCategory qCategory = QCategory.category;

        if (category.getCompany()  != null && category.getCompany().getId() != null) {
            booleanBuilder.and(qCategory.company.id.eq(category.getCompany().getId()));
        }

        List<CategoryCustomDto> categoryCustomDtoList =  new ArrayList<>();
        for (Category categoryLoopItem: Lists.newArrayList(categoryRepository.findAll(booleanBuilder))) {
            CategoryCustomDto categoryCustomDto = new CategoryCustomDto();
            categoryCustomDto.setCategoryCode(categoryLoopItem.getCategoryCode());
            categoryCustomDto.setCategoryImage(categoryLoopItem.getCategoryImage());
            categoryCustomDto.setCategoryName(categoryLoopItem.getCategoryName());
            categoryCustomDto.setId(categoryLoopItem.getId());
            categoryCustomDto.setDescription(categoryLoopItem.getDescription());
            categoryCustomDtoList.add(categoryCustomDto);
        }

        responseDto.setResponseItems(categoryCustomDtoList);
        return responseDto;
    }

    @Override
    public ResponseDto loadAllSub(SubCategory category) {
        ResponseDto responseDto = new ResponseDto();

        if (category.getCompany()  != null && category.getCompany().getId() != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            QSubCategory qCategory = QSubCategory.subCategory;
            booleanBuilder.and(qCategory.company.id.eq(category.getCompany().getId()));

            if (category.getCategory() != null) {
                booleanBuilder.and(qCategory.category.id.eq(category.getCategory().getId()));
            }

            responseDto.setResponseItems(Lists.newArrayList(subCategoryRepository.findAll(booleanBuilder)));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadById(Category category) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseObject(categoryRepository.findById(category.getId()));
        return responseDto;
    }

    @Override
    public ResponseDto removeCategory(Category category) {
        ResponseDto responseDto = new ResponseDto();
        QCategory qCategory = QCategory.category;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QSubCategory qSubCategory = QSubCategory.subCategory;
        BooleanBuilder booleanBuilder1 = new BooleanBuilder();
        booleanBuilder1.and(qSubCategory.category.id.eq(category.getId()));
        List<SubCategory> subCategories = Lists.newArrayList(subCategoryRepository.findAll(booleanBuilder1));

        if (subCategories.size() <= 0) {
            categoryRepository.deleteById(category.getId());
            booleanBuilder.and(qCategory.company.id.eq(category.getCompany().getId()));
            HashMap<String,Long> count = new HashMap<>();
            count.put("count", categoryRepository.count(booleanBuilder));
            responseDto.setStatus(Consts.JOB_SUCCESS);
            responseDto.setResponseObject(count);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Operation failed, Please remove sub category related to this category");
        }

        return responseDto;
    }

    @Override
    public ResponseDto removeSubCategory(SubCategory category) {
        ResponseDto responseDto = new ResponseDto();
        QSubCategory qCategory = QSubCategory.subCategory;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

            QProduct qProduct = QProduct.product;
            BooleanBuilder booleanBuilder1 = new BooleanBuilder();
            booleanBuilder1.and(qProduct.subCategory.id.eq(category.getId()));
            List<Product> products = Lists.newArrayList(productRepository.findAll(booleanBuilder1));

            if (products.size() <= 0) {
                subCategoryRepository.deleteById(category.getId());
                booleanBuilder.and(qCategory.company.id.eq(category.getCompany().getId()));
                HashMap<String,Long> count = new HashMap<>();
                count.put("count", subCategoryRepository.count(booleanBuilder));
                responseDto.setStatus(Consts.JOB_SUCCESS);
                responseDto.setResponseObject(count);
            } else {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Sub category already in use");
            }

        return responseDto;
    }

    public void resourceFileSave(ResourceFile resourceFile) throws Exception{
        String filePath = env.getProperty("file.category-image-path") + resourceFile.getFileName();
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] resFile = base64Decoder.decodeBuffer(resourceFile.getBase64String());
        OutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(resFile);
    }

    public boolean isExists(String categoryName, String mode) {

        QCategory qCategory = QCategory.category;
        QSubCategory qSubCategory= QSubCategory.subCategory;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (mode.equals("CATEGORY")) {
            if(categoryName != null) {
                booleanBuilder.and(qCategory.categoryName.eq(categoryName));
            }

            Optional<Category> loadedCategory = categoryRepository.findOne(booleanBuilder);
            return loadedCategory.isPresent();
        } else {
            if(categoryName != null) {
                booleanBuilder.and(qSubCategory.categoryName.eq(categoryName));
            }

            Optional<SubCategory> loadedCategory = subCategoryRepository.findOne(booleanBuilder);
            return loadedCategory.isPresent();
        }
    }
}
