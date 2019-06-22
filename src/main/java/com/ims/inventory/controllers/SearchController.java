package com.ims.inventory.controllers;

import com.ims.inventory.dtos.SearchResponseDto;
import com.ims.inventory.models.*;
import com.ims.inventory.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/search")
@CrossOrigin("*")
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "supplier", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchSupplier(@RequestBody Vendor vendor) {
        SearchResponseDto searchResponseDto =  searchService.searchSuppliers(vendor);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchCustomer(@RequestBody Customer customer) {
        SearchResponseDto searchResponseDto =  searchService.searchCustomers(customer);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchProduct(@RequestBody Product product) {
        SearchResponseDto searchResponseDto =  searchService.searchProducts(product);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "category", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchCategory(@RequestBody Category category) {
        SearchResponseDto searchResponseDto =  searchService.searchCategories(category);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "sub-category", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchSubCategory(@RequestBody SubCategory category) {
        SearchResponseDto searchResponseDto =  searchService.searchSubCategories(category);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "sales", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchSales(@RequestBody Sales sales) {
        SearchResponseDto searchResponseDto =  searchService.searchSales(sales);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "purchase-orders", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchPurchaseOrders(@RequestBody PurchaseOrder purchaseOrder) {
        SearchResponseDto searchResponseDto =  searchService.searchPurchaseOrder(purchaseOrder);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "stock", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchPurchaseOrders(@RequestBody Stock stock) {
        SearchResponseDto searchResponseDto =  searchService.searchStock(stock);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<SearchResponseDto> searchUsers(@RequestBody User user) {
        SearchResponseDto searchResponseDto =  searchService.searchUsers(user);
        return new ResponseEntity<SearchResponseDto>(searchResponseDto, HttpStatus.OK);
    }

}
