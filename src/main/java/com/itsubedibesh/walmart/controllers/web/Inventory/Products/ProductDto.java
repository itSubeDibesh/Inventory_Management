package com.itsubedibesh.walmart.controllers.web.Inventory.Products;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import java.util.Date;

public class ProductDto {
    public ProductDto() {
    }

    public ProductDto(long categoryId, String productName, String vendorName, String vendorContact, String productImage, String invoiceNumber, Date invoiceDate, Double purchasedQuantity, Double purchasedPrice) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.vendorName = vendorName;
        this.vendorContact = vendorContact;
        this.productImage = productImage;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.purchasedQuantity = purchasedQuantity;
        this.purchasedPrice = purchasedPrice;
    }

    private long id;
    private long categoryId;
    private String productName;
    private String vendorName;
    private String vendorContact;
    private String productImage;
    private String invoiceNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    private Double purchasedQuantity;
    private Double purchasedPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContact() {
        return vendorContact;
    }

    public void setVendorContact(String vendorContact) {
        this.vendorContact = vendorContact;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void setPurchasedQuantity(Double purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    public Double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(Double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    @Transient
    public String getImagePath() {
        if (productImage == null) return null;
        return "src/Product_Images/"+ productImage;
    }
}

