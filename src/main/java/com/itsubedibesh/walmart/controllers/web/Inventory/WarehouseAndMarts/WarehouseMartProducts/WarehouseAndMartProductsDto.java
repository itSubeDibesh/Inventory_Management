package com.itsubedibesh.walmart.controllers.web.Inventory.WarehouseAndMarts.WarehouseMartProducts;

public class WarehouseAndMartProductsDto {
    public WarehouseAndMartProductsDto() {
    }

    public WarehouseAndMartProductsDto(Integer WMId, long productId, Double availableQuantity, Double sellingPrice) {
        this.WMId = WMId;
        ProductId = productId;
        this.availableQuantity = availableQuantity;
        this.sellingPrice = sellingPrice;
    }

    private long id;
    private Integer WMId;
    private long ProductId;
    private Double availableQuantity;
    private Double sellingPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getWMId() {
        return WMId;
    }

    public void setWMId(Integer WMId) {
        this.WMId = WMId;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
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
