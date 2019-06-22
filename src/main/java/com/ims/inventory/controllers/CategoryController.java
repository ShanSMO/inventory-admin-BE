package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.Category;
import com.ims.inventory.models.SubCategory;
import com.ims.inventory.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sys/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createCategory(@RequestBody Category category) {
        ResponseDto responseDto = categoryService.createUpdate(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "create-update-sub", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createSubCategory(@RequestBody SubCategory category) {
        ResponseDto responseDto = categoryService.createUpdateSub(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> search(@RequestBody Category category) {
        ResponseDto responseDto = categoryService.search(category);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-all", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAll(@RequestBody Category category) {
        ResponseDto responseDto = categoryService.loadAll(category);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-all-sub", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadAllSub(@RequestBody SubCategory category) {
        ResponseDto responseDto = categoryService.loadAllSub(category);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "load-by-id", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadById(@RequestBody Category category) {
        ResponseDto responseDto = categoryService.loadById(category);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "remove-cat", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> removeCategory(@RequestBody Category category) {
        ResponseDto responseDto = categoryService.removeCategory(category);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @RequestMapping(value = "remove-sub-cat", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> removeSubCategory(@RequestBody SubCategory category) {
        ResponseDto responseDto = categoryService.removeSubCategory(category);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }


}
