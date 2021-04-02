package com.itsubedibesh.walmart.controllers.api.Inventory.Products;

import com.itsubedibesh.walmart.controllers.api.Inventory.Categories.Categories;
import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Products")
@Entity
public class Products extends Audit {

    public Products() {
    }

    public Products(Categories categoryId, String productName, String vendorName, String productImage, String invoiceNumber, Date invoiceDate, Double purchasedQuantity, Double purchasedPrice) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.vendorName = vendorName;
        this.productImage = productImage;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.purchasedQuantity = purchasedQuantity;
        this.purchasedPrice = purchasedPrice;
    }

    @Id
    @Column(name = "ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "CategoryId",nullable = false)
    private Categories categoryId;

    @Column(name = "ProductName", nullable = false, unique = true)
    private String productName;

    @Column(name = "VendorName", nullable = false)
    private String vendorName;

    @Column(name = "productImage", columnDefinition = "TEXT")
    private String productImage;

    @Column(name = "invoiceNumber", nullable = false,unique = true)
    private String invoiceNumber;

    @Column(name = "invoiceDate", nullable = false,columnDefinition = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    @Column(name = "Quantity", nullable = false)
    private Double purchasedQuantity;

    @Column(name = "Price", nullable = false)
    private Double purchasedPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
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
