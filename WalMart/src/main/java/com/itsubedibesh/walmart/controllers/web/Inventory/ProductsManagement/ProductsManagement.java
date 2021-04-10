package com.itsubedibesh.walmart.controllers.web.Inventory.ProductsManagement;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/products_management")
public class ProductsManagement {

    @GetMapping()
    public String productsManagementViewPage(final Model model, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Products Management");
            return "pages/products_management/products_management";
        } else
            return "redirect:/";
    }
}
