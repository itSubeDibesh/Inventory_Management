package com.itsubedibesh.walmart.controllers.web.Dashboard;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardWeb {

    @GetMapping()
    public String dashboardViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("LoginDetails") != null) {
            model.addAttribute("PageTitle", "Dashboard");
            Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
            model.addAttribute("LoginDetails",loggedUser);
            redirectAttributes.addFlashAttribute("noticeTitle", "Welcome");
            redirectAttributes.addFlashAttribute("noticeMessage", "Welcome "+loggedUser.getUserName()+"!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            return "pages/dashboard/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }
}
