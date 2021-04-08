package com.itsubedibesh.walmart.controllers.api.Mart.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepo extends JpaRepository<Sales,Long> {
    List<Sales> findAllByInvoiceNumber(String invoiceNumber);

    @Query(value = "SELECT * FROM Sales GROUP BY Invoice_Number", nativeQuery = true)
    List<Sales> findAllGroupByInvoiceNumber();

    @Query(value = "SELECT MAX(Invoice_Number)+1 as Invoice_Number FROM Sales", nativeQuery = true)
    String getNewInvoiceNumber();
}