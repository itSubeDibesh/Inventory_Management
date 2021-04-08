package com.itsubedibesh.walmart.controllers.api.Mart.Sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesApi {

    @Autowired
    SalesRepo salesRepo;

    @GetMapping("/sales/{invoiceNumber}")
    public ResponseEntity<List<Sales>> getSalesByInvoice(@PathVariable() String invoiceNumber) {
        try {
            List<Sales> sales = new ArrayList<Sales>();
            salesRepo.findAllByInvoiceNumber(invoiceNumber).forEach(sales::add);
            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/sales/OrderList}")
    public ResponseEntity<List<Sales>> getSalesGroupByInvoice() {
        try {
            List<Sales> sales = new ArrayList<Sales>();
            salesRepo.findAllGroupByInvoiceNumber().forEach(sales::add);
            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
