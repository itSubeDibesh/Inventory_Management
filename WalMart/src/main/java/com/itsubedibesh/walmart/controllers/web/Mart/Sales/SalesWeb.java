package com.itsubedibesh.walmart.controllers.web.Mart.Sales;

import com.itsubedibesh.walmart.controllers.api.Mart.Sales.SalesFunctions;
import com.itsubedibesh.walmart.controllers.api.Mart.Sales.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("billing")
public class SalesWeb {
    @Autowired
    SalesRepo salesRepo;

    @GetMapping()
    public String billingViewPage(final Model model){
        model.addAttribute("PageTitle", "Sales Billing");
        return "/pages/billing/billing";
    }

    @GetMapping("/Add/bill")
    public String createBillViewPage(final Model model) {
        SalesFunctions func = new SalesFunctions(salesRepo);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date= new Date();
        model.addAttribute("PageTitle", "Sales Bill");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "billing");
        model.addAttribute("newInvoiceNumber",func.GetNewInvoiceNumber());
        model.addAttribute("todayDate",format.format(date));
        return "/pages/billing/billingAddEdit";
    }


}
