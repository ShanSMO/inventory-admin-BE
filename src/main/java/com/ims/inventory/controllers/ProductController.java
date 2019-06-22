package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Product;
import com.ims.inventory.models.Stock;
import com.ims.inventory.services.ProductService;
import com.ims.inventory.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "sys/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;


    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createproduct(@RequestBody Product product) {
        ResponseDto responseDto = productService.createUpdate(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "block", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> blockProduct(@RequestBody Product product) {
        ResponseDto responseDto = productService.block(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> search(@RequestBody Product product) {
        ResponseDto responseDto = productService.search(product);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody Product product) {
        ResponseDto responseDto = productService.loadAll(product);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-id", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadById(@RequestBody Product product) {
        ResponseDto responseDto = productService.loadById(product);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-cat-id", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadForCategoryId(@RequestBody Product product) {
        ResponseDto responseDto = productService.loadProductsForCategory(product);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-cat-group", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAllGroupByCategory() {
        ResponseDto responseDto = productService.loadAllGroupByCategory();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-barcode", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadByBarcode(@RequestBody Product product) {
        ResponseDto responseDto = productService.loadByBarcode(product);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> remove(@RequestBody Product product) {
        ResponseDto responseDto = productService.remove(product);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

}
