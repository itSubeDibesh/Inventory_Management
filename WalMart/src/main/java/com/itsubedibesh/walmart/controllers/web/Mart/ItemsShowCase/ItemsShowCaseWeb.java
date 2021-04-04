package com.itsubedibesh.walmart.controllers.web.Mart.ItemsShowCase;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items_showcase")
public class ItemsShowCaseWeb {
    @GetMapping()
    public String itemsShowcaseViewPage(final Model model){
        model.addAttribute("PageTitle", "Items Show Case");
        return "pages/items_showcase/items_showcase";
    }
}
