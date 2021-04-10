package com.itsubedibesh.walmart.controllers.web.Inventory.ProductsManagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products_management")
public class ProductsManagement {

    @GetMapping()
    public String productsManagementViewPage(final Model model) {
        model.addAttribute("PageTitle", "Products Management");
        return "pages/products_management/products_management";
    }
}
