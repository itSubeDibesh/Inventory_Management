package com.itsubedibesh.walmart.controllers.api.Mart.Sales;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.Users;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts.WarehouseAndMartProducts;
import com.itsubedibesh.walmart.controllers.api.Members.Customers.Customers;
import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Sales")
@Entity
public class Sales extends Audit {

    public Sales() {
    }

    public Sales(String invoiceNumber, Date invoiceDate, WarehouseAndMartProducts productId, Customers customerId, Users userId, Double quantity, Double taxAmount, Double returnedAmount, Double receivedAmount) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.productId = productId;
        this.customerId = customerId;
        this.userId = userId;
        this.quantity = quantity;
        this.taxAmount = taxAmount;
        this.returnedAmount = returnedAmount;
        this.receivedAmount = receivedAmount;
    }

    @Id
    @Column(name = "SalesId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "InvoiceNumber", nullable = false)
    private String invoiceNumber;

    @Column(name = "InvoiceDate", nullable = false,columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "ProductId",nullable = false)
    private WarehouseAndMartProducts productId;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "CustomerId",nullable = false)
    private Customers customerId;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "UserId",nullable = false)
    private Users userId;

    @Column(name = "Quantity", nullable = false)
    private Double quantity;

    @Column(name = "taxAmount", nullable = false)
    private Double taxAmount;

    @Column(name = "ReturnedAmount", nullable = false)
    private Double returnedAmount;

    @Column(name = "ReceivedAmount", nullable = false)
    private Double receivedAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public WarehouseAndMartProducts getProductId() {
        return productId;
    }

    public void setProductId(WarehouseAndMartProducts productId) {
        this.productId = productId;
    }

    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getReturnedAmount() {
        return returnedAmount;
    }

    public void setReturnedAmount(Double returnedAmount) {
        this.returnedAmount = returnedAmount;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }
}
