package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.CustomDtos.SalesItemDto;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.*;
import com.ims.inventory.models.QProduct;
import com.ims.inventory.models.QStock;
import com.ims.inventory.repositories.*;
import com.ims.inventory.services.StockService;
import com.ims.inventory.utils.Consts;
import com.ims.inventory.utils.StockStatus;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    PurchaseOrderItemRepository purchaseOrderItemRepository;

    QStock qStock = QStock.stock;

    @Override
    public ResponseDto addUpdateStockItem(Stock stock) {
        ResponseDto responseDto= new ResponseDto();
        if (stock.getCount() > 0) {
            stock.setStatus(StockStatus.AVAILABLE);
        } else {
            stock.setStatus(StockStatus.OUT_OF_STOCK);
        }
        responseDto.setResponseObject(stockRepository.saveAndFlush(stock));
        return responseDto;
    }

    @Override
    public ResponseDto finByProductId(Stock stock) {
        ResponseDto responseDto= new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QStock qStock = QStock.stock;
        booleanBuilder.and(qStock.product.id.eq(stock.getProduct().getId()));
        Optional<Stock> stockItem = stockRepository.findOne(booleanBuilder);
        if (stockItem.isPresent()) {
            responseDto.setResponseObject(stockItem.get());
        } else {
            responseDto.setResponseObject(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadStock() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseItems(stockRepository.findAll());
        return responseDto;
    }

    @Override
    public ResponseDto loadStockForList(Stock stock) {
        ResponseDto responseDto = new ResponseDto();

//        if (stock.getCompany() != null && stock.getCompany().getId() != null) {
//            List<HashMap<String, ?>> stockByCategoryList = new ArrayList<>();
//            QCategory qCategory = QCategory.category;
//            BooleanBuilder booleanBuilderC = new BooleanBuilder(qCategory.company.id.eq(stock.getCompany().getId()));
//
//            for(Category category: categoryRepository.findAll(booleanBuilderC)) {
//                BooleanBuilder booleanBuilder = new BooleanBuilder(qStock.company.id.eq(stock.getCompany().getId()));
//                HashMap<String, Object> stockByCategory = new HashMap<>();
//                booleanBuilder.and(qStock.product.category.id.eq(category.getId()));
//                stockByCategory.put("category", category);
//                stockByCategory.put("stockItem", stockRepository.findAll(booleanBuilder));
//                stockByCategoryList.add(stockByCategory);
//                responseDto.setResponseItems(stockByCategoryList);
//                responseDto.setStatus(Consts.JOB_SUCCESS);
//            }
//        } else {
//            responseDto.setStatus(Consts.JOB_FAILED);
//        }



        QSubCategory qSubCategory = QSubCategory.subCategory;

        if (stock.getCompany() != null && stock.getCompany().getId() != null) {
            List<HashMap<String, ?>> stockByCategoryList = new ArrayList<>();
            QCategory qCategory = QCategory.category;
            BooleanBuilder booleanBuilderC = new BooleanBuilder(qCategory.company.id.eq(stock.getCompany().getId()));

            for(Category category: categoryRepository.findAll(booleanBuilderC)) {

                BooleanBuilder booleanBuilderSubCat = new BooleanBuilder();
                booleanBuilderSubCat.and(qSubCategory.category.id.eq(category.getId()));

                HashMap<String, Object> stockByCategory = new HashMap<>();
                stockByCategory.put("category", category);

                List<HashMap<String, ?>> stockBySubCategoryList = new ArrayList<>();

                for (SubCategory subCategory: Lists.newArrayList(subCategoryRepository.findAll(booleanBuilderSubCat))) {
                    HashMap<String, Object> stockBySubCategory = new HashMap<>();
                    BooleanBuilder booleanBuilder = new BooleanBuilder(qStock.company.id.eq(stock.getCompany().getId()));
                    booleanBuilder.and(qStock.product.subCategory.id.eq(subCategory.getId()));
                    stockBySubCategory.put("category", subCategory);
                    stockBySubCategory.put("stockItem", stockRepository.findAll(booleanBuilder));
                    stockBySubCategoryList.add(stockBySubCategory);
                }

                stockByCategory.put("items", stockBySubCategoryList);
                stockByCategoryList.add(stockByCategory);

                responseDto.setResponseItems(stockByCategoryList);
                responseDto.setStatus(Consts.JOB_SUCCESS);
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }


    @Override
    public ResponseDto loadByBarcode(Stock stock) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qStock.barcode.eq(stock.getBarcode()));
        Optional<Stock> record = stockRepository.findOne(booleanBuilder);
        if (record.isPresent()) {
            responseDto.setResponseObject(record.get());
            Stock stockRecord = record.get();
            SalesItemDto salesItemDto = new SalesItemDto();
            stockRecord.getProduct();
            stockRecord.getStatus();
            stockRecord.getId();
        } else {
            responseDto.setResponseObject(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadByProduct(Stock stock) {
        ResponseDto responseDto = new ResponseDto();

        QProduct qProduct = QProduct.product;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (stock.getProduct().getId() != null) {
            booleanBuilder.and(qProduct.id.eq(stock.getProduct().getId()));
        }

        Optional<Product> selectedProductOpt = productRepository.findOne(booleanBuilder);
        if(selectedProductOpt.isPresent()) {
            Product selectedProduct = selectedProductOpt.get();
            com.ims.inventory.models.QPurchaseOrderItem qPurchaseOrderItem = com.ims.inventory.models.QPurchaseOrderItem.purchaseOrderItem;
            BooleanBuilder booleanBuilder1 = new BooleanBuilder();
            booleanBuilder1.and(qPurchaseOrderItem.product.id.eq(selectedProduct.getId()));
            Iterable<PurchaseOrderItem> orderItems = purchaseOrderItemRepository.findAll(booleanBuilder1);
            responseDto.setResponseItems(Lists.newArrayList(orderItems));

            QStock qStock = QStock.stock;
            BooleanBuilder booleanBuilder2 = new BooleanBuilder();
            booleanBuilder2.and(qStock.product.id.eq(stock.getProduct().getId()));
            Optional<Stock> stockLoaded = stockRepository.findOne(booleanBuilder2);
            responseDto.setResponseObject(stockLoaded.orElse(null));
        }

        return responseDto;
    }
}
