package com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts;

import com.itsubedibesh.walmart.controllers.api.Inventory.Products.Products;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart.WareHouseAndMart;
import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "WarehouseAndMartProducts")
public class WarehouseAndMartProducts extends Audit {

    public WarehouseAndMartProducts() {
    }

    public WarehouseAndMartProducts(WareHouseAndMart WMId, Products productId, Double availableQuantity, Double sellingPrice) {
        this.WMId = WMId;
        ProductId = productId;
        this.availableQuantity = availableQuantity;
        this.sellingPrice = sellingPrice;
    }

    @Id
    @Column(name = "WMProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "WMId",nullable = false)
    private WareHouseAndMart WMId;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "ProductId",nullable = false)
    private Products ProductId;

    @Column(name = "AvailableQuantity", nullable = false)
    private Double availableQuantity;

    @Column(name = "SellingPrice", nullable = false)
    private Double sellingPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WareHouseAndMart getWMId() {
        return WMId;
    }

    public void setWMId(WareHouseAndMart WMId) {
        this.WMId = WMId;
    }

    public Products getProductId() {
        return ProductId;
    }

    public void setProductId(Products productId) {
        ProductId = productId;
    }

    public Double getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
