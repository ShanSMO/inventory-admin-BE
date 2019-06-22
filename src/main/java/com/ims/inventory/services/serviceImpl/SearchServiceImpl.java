package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.SearchResponseDto;
import com.ims.inventory.models.*;
import com.ims.inventory.models.QCategory;
import com.ims.inventory.models.QCustomer;
import com.ims.inventory.models.QProduct;
import com.ims.inventory.models.QPurchaseOrder;
import com.ims.inventory.models.QSales;
import com.ims.inventory.models.QStock;
import com.ims.inventory.models.QUser;
import com.ims.inventory.models.QVendor;
import com.ims.inventory.repositories.*;
import com.ims.inventory.services.SearchService;
import com.ims.inventory.utils.Consts;
import com.ims.inventory.utils.PagingSortingAndOrdering;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.PredicateOperation;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SalesRepository  salesRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    UserRepository userRepository;

    private static Integer RECORDS_PER_PAGE = 10;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar calendar = Calendar.getInstance();

    @Override
    public SearchResponseDto searchSuppliers(Vendor vendor) {

        SearchResponseDto searchResponseDto = new SearchResponseDto();
        QVendor qVendor = QVendor.vendor;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Iterable<Vendor> vendors;

        if (vendor.getCompany() != null && vendor.getCompany().getId() != null) {
            booleanBuilder.and(qVendor.company.id.eq(vendor.getCompany().getId()));

            if (vendor.getEmailAddress() != null) {
                booleanBuilder.andAnyOf(qVendor.emailAddress.contains(vendor.getEmailAddress()));
            }
            if (vendor.getFirstName() != null) {
                booleanBuilder.andAnyOf(qVendor.firstName.contains(vendor.getFirstName()));
            }
            if (vendor.getPhoneNumber() != null) {
                booleanBuilder.andAnyOf(qVendor.phoneNumber.like(Expressions.asString("%").concat(vendor.getPhoneNumber().toString()).concat("%")));
            }

            if (vendor.getSortingField() != null) {
                vendors = vendorRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(vendor.getCurrentPage(),RECORDS_PER_PAGE,
                                vendor.getSortingField(), vendor.getSortingDirection()));
            } else {
                vendors = vendorRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(vendor.getCurrentPage(),RECORDS_PER_PAGE));
            }
            searchResponseDto.setSearchItems(Lists.newArrayList(vendors));
            searchResponseDto.setRecordCount(vendorRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchCustomers(Customer customer) {

        SearchResponseDto searchResponseDto = new SearchResponseDto();
        QCustomer qCustomer = QCustomer.customer;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Iterable<Customer> customers;

        if (customer.getCompany() != null && customer.getCompany().getId() != null) {
            booleanBuilder.and(qCustomer.company.id.eq(customer.getCompany().getId()));

            if (customer.getEmailAddress() != null) {
                booleanBuilder.andAnyOf(qCustomer.emailAddress.contains(customer.getEmailAddress()));
            }
            if (customer.getFirstName() != null) {
                booleanBuilder.andAnyOf(qCustomer.firstName.contains(customer.getFirstName()));
            }
            if (customer.getPhoneNumber() != null) {
                booleanBuilder.andAnyOf(qCustomer.phoneNumber.like(Expressions.asString("%").concat(customer.getPhoneNumber().toString()).concat("%")));
            }


            if (customer.getSortingField() != null)  {
                customers = customerRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(customer.getCurrentPage(),RECORDS_PER_PAGE,
                                customer.getSortingField(), customer.getSortingDirection()));
            } else {
                customers = customerRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(customer.getCurrentPage(),RECORDS_PER_PAGE));
            }

            searchResponseDto.setSearchItems(Lists.newArrayList(customers));
            searchResponseDto.setRecordCount(customerRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchProducts(Product product) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        QProduct qProduct = QProduct.product;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Iterable<Product> products;

        if (product.getCompany() != null && product.getCompany().getId() != null) {
            booleanBuilder.and(qProduct.company.id.eq(product.getCompany().getId()));

            if (product.getProductCode() != null) {
                booleanBuilder.andAnyOf(qProduct.productCode.contains(product.getProductCode()));
            }
            if (product.getBarcode() != null) {
                booleanBuilder.andAnyOf(qProduct.barcode.contains(product.getBarcode()));
            }
            if (product.getProductName() != null) {
                booleanBuilder.andAnyOf(qProduct.productName.contains(product.getProductName()));
            }

            if (product.getBrand() != null && product.getBrand().getId() != null) {
                booleanBuilder.andAnyOf(qProduct.brand.id.eq(product.getBrand().getId()));
            }

            if (product.getBarcode() != null && product.getBarcode() != null) {
                booleanBuilder.andAnyOf(qProduct.barcode.eq(product.getBarcode()));
            }

            if (product.getCategory() != null && product.getCategory().getId() != null) {
                booleanBuilder.andAnyOf(qProduct.category.id.eq(product.getCategory().getId()));
            }

            if (product.getSubCategory() != null && product.getSubCategory().getId() != null) {
                booleanBuilder.andAnyOf(qProduct.subCategory.id.eq(product.getSubCategory().getId()));
            }

            if (product.getStatus() != null) {
                booleanBuilder.andAnyOf(qProduct.status.eq(product.getStatus()));
            }

            if (product.getSortingField() != null) {
                products = productRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(product.getCurrentPage(),RECORDS_PER_PAGE,
                                product.getSortingField(), product.getSortingDirection()));
            } else {
                products = productRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(product.getCurrentPage(),RECORDS_PER_PAGE));
            }

            searchResponseDto.setSearchItems(Lists.newArrayList(products));
            searchResponseDto.setRecordCount(productRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchCategories(Category category) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();

        if (category.getCompany() != null && category.getCompany().getId() != null) {
            QCategory qCategory = QCategory.category;
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            Iterable<Category> categories;

            booleanBuilder.and(qCategory.company.id.eq(category.getCompany().getId()));

            if (category.getCategoryName() != null) {
                booleanBuilder.andAnyOf(qCategory.categoryName.contains(category.getCategoryName()));
            }

            if (category.getSortingField() != null) {
                categories = categoryRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(category.getCurrentPage(),RECORDS_PER_PAGE,
                                category.getSortingField(), category.getSortingDirection()));
            } else {
                categories = categoryRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(category.getCurrentPage(),RECORDS_PER_PAGE));
            }

            searchResponseDto.setSearchItems(Lists.newArrayList(categories));
            searchResponseDto.setRecordCount(categoryRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchSubCategories(SubCategory category) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();

        if (category.getCompany() != null && category.getCompany().getId() != null) {
            QSubCategory qCategory = QSubCategory.subCategory;
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            Iterable<SubCategory> categories;
            booleanBuilder.and(qCategory.company.id.eq(category.getCompany().getId()));

            if (category.getCategory() != null && category.getCategory().getId() != null) {
                booleanBuilder.andAnyOf(qCategory.category.id.eq(category.getCategory().getId()));
            }

            if (category.getCategoryName() != null) {
                booleanBuilder.andAnyOf(qCategory.categoryName.contains(category.getCategoryName()));
            }

            if (category.getSortingField() != null) {
                categories = subCategoryRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(category.getCurrentPage(),RECORDS_PER_PAGE,
                                category.getSortingField(), category.getSortingDirection()));
            } else {
                categories = subCategoryRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(category.getCurrentPage(),RECORDS_PER_PAGE));
            }

            searchResponseDto.setSearchItems(Lists.newArrayList(categories));
            searchResponseDto.setRecordCount(subCategoryRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);

        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchSales(Sales sales) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();

        if (sales.getCompany() != null && sales.getCompany().getId() != null) {
            QSales qSales = QSales.sales;
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            Iterable<Sales> salesIterable;

            booleanBuilder.and(qSales.company.id.eq(sales.getCompany().getId()));

            if (sales.getCustomer() != null) {
                if (sales.getCustomer().getFirstName() != null) {
                    booleanBuilder.andAnyOf(qSales.customer.firstName.contains(sales.getCustomer().getFirstName()));
                }
            }

            if (sales.getFromDate() != null && sales.getToDate() != null) {
                Date fromDate = new Date();
                Date toDate = new Date();
                try {
                    calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(sales.getFromDate()).concat(" 00:00:00")));
                    fromDate = calendar.getTime();
                    calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(sales.getToDate()).concat(" 23:59:59")));
                    toDate = calendar.getTime();
                } catch (Exception e){
                    e.printStackTrace();
                }
                booleanBuilder.and(qSales.saleDate.between(fromDate,toDate));

                booleanBuilder.and(qSales.saleDate.between(sales.getFromDate(),sales.getToDate()));
            } else {
                if (sales.getFromDate() != null) {
                    Date fromDate = new Date();
                    Date toDate = new Date();
                    try {
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(sales.getFromDate()).concat(" 00:00:00")));
                        fromDate = calendar.getTime();
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(sales.getFromDate()).concat(" 23:59:59")));
                        toDate = calendar.getTime();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    booleanBuilder.and(qSales.saleDate.between(fromDate,toDate));
                } else if (sales.getToDate() != null)  {
                    Date fromDate = new Date();
                    Date toDate = new Date();
                    try {
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(sales.getToDate()).concat(" 00:00:00")));
                        fromDate = calendar.getTime();
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(sales.getToDate()).concat(" 23:59:59")));
                        toDate = calendar.getTime();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    booleanBuilder.and(qSales.saleDate.between(fromDate,toDate));
                } else {}
            }

            if (sales.getSortingField() != null) {
                salesIterable = salesRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(sales.getCurrentPage(),RECORDS_PER_PAGE,
                                sales.getSortingField(), sales.getSortingDirection()));
            } else {
                salesIterable = salesRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(sales.getCurrentPage(),RECORDS_PER_PAGE));
            }

            searchResponseDto.setSearchItems(Lists.newArrayList(salesIterable));
            searchResponseDto.setRecordCount(salesRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);

        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchPurchaseOrder(PurchaseOrder purchaseOrder) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Iterable<PurchaseOrder> salesIterable;

        if (purchaseOrder.getCompany() != null && purchaseOrder.getCompany().getId() != null) {
            booleanBuilder.and(qPurchaseOrder.company.id.eq(purchaseOrder.getCompany().getId()));
            if (purchaseOrder.getVendor() != null && purchaseOrder.getVendor().getFirstName() != null) {
                booleanBuilder.andAnyOf(qPurchaseOrder.vendor.firstName.contains(purchaseOrder.getVendor().getFirstName()));
            }

            if (purchaseOrder.getFromDate() != null && purchaseOrder.getToDate() != null) {
                Date fromDate = new Date();
                Date toDate = new Date();
                try {
                    calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(purchaseOrder.getFromDate()).concat(" 00:00:00")));
                    fromDate = calendar.getTime();
                    calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(purchaseOrder.getToDate()).concat(" 23:59:59")));
                    toDate = calendar.getTime();
                } catch (Exception e){
                    e.printStackTrace();
                }
                booleanBuilder.and(qPurchaseOrder.createdDate.between(fromDate,toDate));

                booleanBuilder.and(qPurchaseOrder.createdDate.between(purchaseOrder.getFromDate(),purchaseOrder.getToDate()));
            } else {
                if (purchaseOrder.getFromDate() != null) {
                    Date fromDate = new Date();
                    Date toDate = new Date();
                    try {
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(purchaseOrder.getFromDate()).concat(" 00:00:00")));
                        fromDate = calendar.getTime();
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(purchaseOrder.getFromDate()).concat(" 23:59:59")));
                        toDate = calendar.getTime();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    booleanBuilder.and(qPurchaseOrder.createdDate.between(fromDate,toDate));
                } else if (purchaseOrder.getToDate() != null)  {
                    Date fromDate = new Date();
                    Date toDate = new Date();
                    try {
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(purchaseOrder.getToDate()).concat(" 00:00:00")));
                        fromDate = calendar.getTime();
                        calendar.setTime(simpleDateFormat2.parse(simpleDateFormat.format(purchaseOrder.getToDate()).concat(" 23:59:59")));
                        toDate = calendar.getTime();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    booleanBuilder.and(qPurchaseOrder.createdDate.between(fromDate,toDate));
                } else {}
            }

            if (purchaseOrder.getPurchaseOrderId() != null) {
                booleanBuilder.and(qPurchaseOrder.purchaseOrderId.contains(purchaseOrder.getPurchaseOrderId()));
            }

            if (purchaseOrder.getSortingField() != null) {
                salesIterable = purchaseOrderRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(purchaseOrder.getCurrentPage(),RECORDS_PER_PAGE,
                                purchaseOrder.getSortingField(), purchaseOrder.getSortingDirection()));
            } else {
                salesIterable = purchaseOrderRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(purchaseOrder.getCurrentPage(),RECORDS_PER_PAGE));
            }

            searchResponseDto.setSearchItems(Lists.newArrayList(salesIterable));
            searchResponseDto.setRecordCount(purchaseOrderRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchStock(Stock stock) {

        SearchResponseDto searchResponseDto = new SearchResponseDto();

        if (stock.getCompany() != null && stock.getCompany().getId() != null) {
            QStock qStock = QStock.stock;
            List<HashMap<String, ?>> stockByCategoryList = new ArrayList<>();
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.and(qStock.company.id.eq(stock.getCompany().getId()));
            QSubCategory qSubCategory = QSubCategory.subCategory;

            if (stock.getCategory() != null && stock.getCategory().getId() != null) {

                booleanBuilder = new BooleanBuilder(qStock.company.id.eq(stock.getCompany().getId()));
                HashMap<String, Object> stockByCategory = new HashMap<>();

                stockByCategory.put("category", categoryRepository.findById(stock.getCategory().getId()));
                BooleanBuilder booleanBuilderSubCat = new BooleanBuilder();
                booleanBuilderSubCat.and(qSubCategory.category.id.eq(stock.getCategory().getId()));

                List<HashMap<String, ?>> stockBySubCategoryList = new ArrayList<>();
                for (SubCategory subCategory: Lists.newArrayList(subCategoryRepository.findAll(booleanBuilderSubCat))) {
                    HashMap<String, Object> stockBySubCategory = new HashMap<>();
                    booleanBuilder.and(qStock.product.subCategory.id.eq(subCategory.getId()));
                    stockBySubCategory.put("category", subCategory);
                    stockBySubCategory.put("stockItem", stockRepository.findAll(booleanBuilder));
                    stockBySubCategoryList.add(stockBySubCategory);
                }
                stockByCategory.put("items", stockBySubCategoryList);
                stockByCategoryList.add(stockByCategory);
            } else {


                for (Category category : categoryRepository.findAll()) {
                    BooleanBuilder booleanBuilderSubCat = new BooleanBuilder();
                    booleanBuilderSubCat.and(qSubCategory.category.id.eq(category.getId()));

                    booleanBuilder = new BooleanBuilder();
                    HashMap<String, Object> stockByCategory = new HashMap<>();
                    stockByCategory.put("category", category);

                    List<HashMap<String, ?>> stockBySubCategoryList = new ArrayList<>();
                    for (SubCategory subCategory: Lists.newArrayList(subCategoryRepository.findAll(booleanBuilderSubCat))) {

                        HashMap<String, Object> stockBySubCategory = new HashMap<>();
                        booleanBuilder.and(qStock.product.subCategory.id.eq(subCategory.getId()));
                        stockBySubCategory.put("category", subCategory);
                        stockBySubCategory.put("stockItem", stockRepository.findAll(booleanBuilder));
                        stockBySubCategoryList.add(stockBySubCategory);
                    }
                    stockByCategory.put("items", stockBySubCategoryList);
                    stockByCategoryList.add(stockByCategory);
                }
            }

            searchResponseDto.setSearchItems(stockByCategoryList);
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);

        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }

    @Override
    public SearchResponseDto searchUsers(User user) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Iterable<User> users;

        if (user.getCompany() != null && user.getCompany().getId() != null) {
            booleanBuilder.and(qUser.company.id.eq(user.getCompany().getId()));

            if (user.getSortingField() != null) {
                users = userRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(user.getCurrentPage(),5,
                                user.getSortingField(), user.getSortingDirection()));
            } else {
                users = userRepository.findAll(booleanBuilder,
                        PagingSortingAndOrdering.createPageRequest(user.getCurrentPage(),5));
            }

            searchResponseDto.setSearchItems(Lists.newArrayList(users));
            searchResponseDto.setRecordCount(userRepository.count(booleanBuilder));
            searchResponseDto.setStatus(Consts.JOB_SUCCESS);
        } else {
            searchResponseDto.setStatus(Consts.JOB_FAILED);
        }

        return searchResponseDto;
    }
}
