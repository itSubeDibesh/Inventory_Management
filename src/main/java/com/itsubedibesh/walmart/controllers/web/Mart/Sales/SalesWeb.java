package com.itsubedibesh.walmart.controllers.web.Mart.Sales;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Mart.Sales.SalesFunctions;
import com.itsubedibesh.walmart.controllers.api.Mart.Sales.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("billing")
public class SalesWeb {
    @Autowired
    SalesRepo salesRepo;

    @GetMapping()
    public String billingViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Sales Billing");
            return "/pages/billing/billing";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Add/bill")
    public String createBillViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            SalesFunctions func = new SalesFunctions(salesRepo);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            model.addAttribute("PageTitle", "Sales Bill");
            model.addAttribute("Action", "Add");
            model.addAttribute("BaseLink", "billing");
            model.addAttribute("newInvoiceNumber", func.GetNewInvoiceNumber());
            model.addAttribute("todayDate", format.format(date));
            return "/pages/billing/billingAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/View/{invoiceNumber}")
    public String viewBillingPage(@PathVariable() String invoiceNumber, final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Sales Bill");
            model.addAttribute("Action", "View");
            model.addAttribute("BaseLink", "billing");
            model.addAttribute("invoiceNumber", invoiceNumber);
            return "/pages/billing/billingView";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

}
