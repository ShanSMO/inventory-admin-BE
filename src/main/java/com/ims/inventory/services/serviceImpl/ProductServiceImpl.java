package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.ResourceFile;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.enums.ActiveStatus;
import com.ims.inventory.models.*;
import com.ims.inventory.models.QProduct;
import com.ims.inventory.models.QPurchaseOrder;
import com.ims.inventory.models.QPurchaseOrderItem;
import com.ims.inventory.models.QStock;
import com.ims.inventory.repositories.ProductRepository;
import com.ims.inventory.repositories.PurchaseOrderItemRepository;
import com.ims.inventory.repositories.PurchaseOrderRepository;
import com.ims.inventory.repositories.StockRepository;
import com.ims.inventory.services.ProductService;
import com.ims.inventory.services.StockService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:common.properties")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockService stockService;

    @Autowired
    PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Autowired
    private Environment env;

    @Override
    public ResponseDto createUpdate(Product product) {
        Long prductId = product.getId();
        ResponseDto responseDto = new ResponseDto();


        if (product != null && product.getBarcode() != null) {
            QProduct qProduct = QProduct.product;
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.and(qProduct.barcode.eq(product.getBarcode()));
            if (productRepository.findOne(booleanBuilder).isPresent() && product.getId() == null) {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Barcode already exists");
            } else {
                if (product.getId() == null) {
                    product.setStatus(ActiveStatus.ACTIVE);
                }

                try {
                    if (product.getResourceFile() != null && product.getResourceFile().getBase64String() != null) {
                        resourceFileSave(product.getResourceFile());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (product.getResourceFile() != null && product.getResourceFile().getBase64String() != null) {
                    product.setProductImageUrl(env.getProperty("file.product-image-path-public") + product.getResourceFile().getFileName());
                }

                Product savedProduct = productRepository.saveAndFlush(product);
                savedProduct.setProductCode(CodeGenerator.generateCode(Consts.GEN_TYPE_PRODUCT, savedProduct.getId()));
                responseDto.setResponseObject(savedProduct);

                if(prductId == null) {
                    Stock stock = new Stock();
                    stock.setBarcode(product.getBarcode());
                    stock.setAddedDate(new Date());
                    stock.setProduct(product);
                    stock.setCount(0);
                    stock.setCompany(product.getCompany());
                    stockService.addUpdateStockItem(stock);
                }
                responseDto.setStatus(Consts.JOB_SUCCESS);
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    @Override
    public ResponseDto block(Product product) {
        ResponseDto responseDto = new ResponseDto();
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            Product loadedProduct = productOptional.get();
            loadedProduct.setStatus(ActiveStatus.BLOCKED);
            productRepository.saveAndFlush(loadedProduct);
            responseDto.setMessage("Success");
        } else {
            responseDto.setMessage("Request failed");
        }
        return responseDto;
    }

    @Override
    public ResponseDto search(Product product) {
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }

    @Override
    public ResponseDto loadAll(Product product) {
        ResponseDto responseDto = new ResponseDto();

        if (product.getCompany() != null && product.getCompany().getId() != null) {
            QProduct qProduct = QProduct.product;
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.and(qProduct.company.id.eq(product.getCompany().getId()));
            responseDto.setResponseItems(Lists.newArrayList(productRepository.findAll(booleanBuilder)));
            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadById(Product product) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseObject(productRepository.findById(product.getId()));
        return responseDto;
    }

    public void resourceFileSave(ResourceFile resourceFile) throws Exception{

        String filePath = env.getProperty("file.product-image-path") + resourceFile.getFileName();
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] resFile = base64Decoder.decodeBuffer(resourceFile.getBase64String());
        OutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(resFile);

    }

    @Override
    public ResponseDto loadProductsForCategory(Product product) {
        ResponseDto responseDto = new ResponseDto();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QProduct qProduct = QProduct.product;
        booleanBuilder.and(qProduct.category.id.eq(product.getCategory().getId()));
        Iterable<Product> productList =  productRepository.findAll(booleanBuilder);

        responseDto.setResponseItems(Lists.newArrayList(productList));
        return responseDto;
    }

    @Override
    public ResponseDto loadAllGroupByCategory() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseItems(productRepository.loadProductGroupByCategory());
        return responseDto;
    }

    @Override
    public ResponseDto loadByBarcode(Product product) {
        ResponseDto responseDto = new ResponseDto();

        if (product.getCompany() != null && product.getCompany().getId() != null) {
            if (product.getBarcode() != null && !product.getBarcode().equals("")) {

                QProduct qProduct = QProduct.product;
                BooleanBuilder booleanBuilder = new BooleanBuilder(qProduct.company.id.eq(product.getCompany().getId()));

                if (product.getBarcode() != null) {
                    booleanBuilder.and(qProduct.barcode.eq(product.getBarcode()));
                }

                booleanBuilder.and(qProduct.status.eq(ActiveStatus.ACTIVE));

                Optional<Product> selectedProductOpt = productRepository.findOne(booleanBuilder);
                if(selectedProductOpt.isPresent()) {
                    Product selectedProduct = selectedProductOpt.get();
                    com.ims.inventory.models.QPurchaseOrderItem qPurchaseOrderItem = QPurchaseOrderItem.purchaseOrderItem;
                    BooleanBuilder booleanBuilder1 = new BooleanBuilder();
                    booleanBuilder1.and(qPurchaseOrderItem.product.id.eq(selectedProduct.getId()));
                    Iterable<PurchaseOrderItem> orderItems = purchaseOrderItemRepository.findAll(booleanBuilder1);
                    responseDto.setResponseItems(Lists.newArrayList(orderItems));

                    QStock qStock = QStock.stock;
                    BooleanBuilder booleanBuilder2 = new BooleanBuilder();
                    booleanBuilder2.and(qStock.product.id.eq(selectedProduct.getId()));
                    Optional<Stock> stock = stockRepository.findOne(booleanBuilder2);
                    responseDto.setResponseObject(stock.orElse(null));
                }
            }

            responseDto.setStatus(Consts.JOB_SUCCESS);
        } else  {
            responseDto.setStatus(Consts.JOB_FAILED);
        }

        return responseDto;
    }

    @Override
    public ResponseDto remove(Product product) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QProduct qProduct = QProduct.product;

        if (product.getCompany() !=null && product.getCompany().getId() != null && product.getId() != null) {

            booleanBuilder.and(qProduct.company.id.eq(product.getCompany().getId()));
            booleanBuilder.and(qProduct.id.eq(product.getId()));

            if (productRepository.findOne(booleanBuilder).isPresent()) {

                QStock qStock = QStock.stock;
                BooleanBuilder booleanBuilder1 = new BooleanBuilder();
                booleanBuilder1.and(qStock.product.id.eq(product.getId()));
                Optional<Stock> stockOptional = stockRepository.findOne(booleanBuilder1);
                if (stockOptional.isPresent() && stockOptional.get().getCount() <= 0) {
                    stockRepository.deleteById(stockOptional.get().getId());
                    productRepository.deleteById(product.getId());
                    responseDto.setStatus(Consts.JOB_SUCCESS);
                } else {
                    responseDto.setStatus(Consts.JOB_FAILED);
                    responseDto.setMessage("Product already in Use");
                }

            } else {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Invalid Request");
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Invalid Request");
        }

        return responseDto;
    }
}
