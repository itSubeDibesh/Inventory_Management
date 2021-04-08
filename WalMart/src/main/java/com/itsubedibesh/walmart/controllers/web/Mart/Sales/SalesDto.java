package com.itsubedibesh.walmart.controllers.web.Mart.Sales;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SalesDto {
    public SalesDto() {
    }

    public SalesDto(Date invoiceDate, long productId, long customerId, long userId, Double quantity, Double receivedAmount, Double returnedAmount) {
        this.invoiceDate = invoiceDate;
        this.productId = productId;
        this.customerId = customerId;
        this.userId = userId;
        this.quantity = quantity;
        this.receivedAmount = receivedAmount;
        this.returnedAmount = returnedAmount;
    }

    private long id;
    private String invoiceNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    private long productId;
    private long customerId;
    private long userId;
    private Double quantity;
    private Double receivedAmount;
    private Double returnedAmount;

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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getReturnedAmount() {
        return returnedAmount;
    }

    public void setReturnedAmount(Double returnedAmount) {
        this.returnedAmount = returnedAmount;
    }
}
