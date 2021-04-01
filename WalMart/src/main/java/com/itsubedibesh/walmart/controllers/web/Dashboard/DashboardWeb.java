package com.itsubedibesh.walmart.controllers.web.Dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardWeb {

    @GetMapping()
    public String dashboardViewPage(final Model model){
        model.addAttribute("PageTitle","Dashboard");
        return "pages/dashboard/dashboard";
    }
}
