package com.ims.inventory.services;

import com.ims.inventory.dtos.SearchResponseDto;
import com.ims.inventory.models.*;

import javax.transaction.Transactional;

@Transactional
public interface SearchService {

    SearchResponseDto searchSuppliers(Vendor vendor);
    SearchResponseDto searchCustomers(Customer customer);
    SearchResponseDto searchProducts(Product product);
    SearchResponseDto searchCategories(Category category);
    SearchResponseDto searchSubCategories(SubCategory category);
    SearchResponseDto searchSales(Sales sales);
    SearchResponseDto searchPurchaseOrder(PurchaseOrder purchaseOrder);
    SearchResponseDto searchStock(Stock stock);
    SearchResponseDto searchUsers(User user);

}
