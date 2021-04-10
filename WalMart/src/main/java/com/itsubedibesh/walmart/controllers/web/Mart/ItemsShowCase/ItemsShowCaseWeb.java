package com.itsubedibesh.walmart.controllers.web.Mart.ItemsShowCase;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/items_showcase")
public class ItemsShowCaseWeb {
    @GetMapping()
    public String itemsShowcaseViewPage(final Model model, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Items Show Case");
            return "pages/items_showcase/items_showcase";
        } else
            return "redirect:/";
    }
}
