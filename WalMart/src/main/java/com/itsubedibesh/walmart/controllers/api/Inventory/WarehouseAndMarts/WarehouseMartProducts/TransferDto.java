package com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts;

import com.itsubedibesh.walmart.controllers.api.Inventory.Products.Products;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart.WareHouseAndMart;

public class TransferDto {
    public TransferDto() {
    }

    public TransferDto(WareHouseAndMart sendingMart, WareHouseAndMart receivingMart, Products sendingProduct, Double quantity) {
        this.sendingMart = sendingMart;
        this.receivingMart = receivingMart;
        this.sendingProduct = sendingProduct;
        this.quantity = quantity;
    }

    private WareHouseAndMart sendingMart;
    private WareHouseAndMart receivingMart;
    private Products sendingProduct;
    private Double quantity;

    public WareHouseAndMart getSendingMart() {
        return sendingMart;
    }

    public void setSendingMart(WareHouseAndMart sendingMart) {
        this.sendingMart = sendingMart;
    }

    public WareHouseAndMart getReceivingMart() {
        return receivingMart;
    }

    public void setReceivingMart(WareHouseAndMart receivingMart) {
        this.receivingMart = receivingMart;
    }

    public Products getSendingProduct() {
        return sendingProduct;
    }

    public void setSendingProduct(Products sendingProduct) {
        this.sendingProduct = sendingProduct;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
