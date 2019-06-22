package com.ims.inventory.dtos.CustomDtos;

import com.ims.inventory.models.Product;
import com.ims.inventory.models.PurchaseOrderItem;
import com.ims.inventory.models.Stock;

public class SalesItemDto {

    private Product product;
    private PurchaseOrderItem purchaseOrderItem;
    private Stock stock;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PurchaseOrderItem getPurchaseOrderItem() {
        return purchaseOrderItem;
    }

    public void setPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        this.purchaseOrderItem = purchaseOrderItem;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
