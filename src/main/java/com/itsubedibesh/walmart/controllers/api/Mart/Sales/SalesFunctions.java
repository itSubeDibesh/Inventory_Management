package com.itsubedibesh.walmart.controllers.api.Mart.Sales;

public class SalesFunctions {

    private SalesRepo salesRepo;

    public SalesFunctions(SalesRepo salesRepo) {
        this.salesRepo = salesRepo;
    }

    public String GetNewInvoiceNumber() {
        String invoice = salesRepo.getNewInvoiceNumber();
        if (invoice == null || invoice.isEmpty())
            return "52301";
        else
            return invoice.substring(0,invoice.lastIndexOf("."));
    }
}
