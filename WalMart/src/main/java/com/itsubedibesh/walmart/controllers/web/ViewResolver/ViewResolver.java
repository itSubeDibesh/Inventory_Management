package com.itsubedibesh.walmart.controllers.web.ViewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewResolver {


    @GetMapping("products")
    public String productsPage(final Model model) {
        model.addAttribute("PageTitle", "Products");
        return "pages/products/products";
    }

    @GetMapping("dashboard")
    public String dashboardPage(final Model model){
        model.addAttribute("PageTitle","Dashboard");
        return "pages/dashboard/dashboard";
    }
}