package com.itsubedibesh.walmart.controllers.web.Inventory.Products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductWeb {

    @GetMapping()
    public String productsViewPage(final Model model) {
        model.addAttribute("PageTitle", "Products");
        return "pages/products/products";
    }
}
