package com.itsubedibesh.walmart.controllers.web.Members.Customers;

import com.itsubedibesh.walmart.controllers.api.Members.Customers.Customers;
import com.itsubedibesh.walmart.controllers.api.Members.Customers.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomersWeb {

    @Autowired
    CustomersRepo customersRepo;

    @GetMapping()
    public String customersViewPage(final Model model){
        model.addAttribute("PageTitle", "Customers");
        return "pages/customers/customers";
    }

    @GetMapping("/Add/customer")
    public String createCustomersViewPage(final Model model) {
        model.addAttribute("PageTitle", "Customers");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "customers");
        return "pages/customers/customersAddEdit";
    }

    @GetMapping("/Update/customer/{customerId}")
    public String updateCustomersViewPage(@PathVariable() long customerId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<Customers> customerData = customersRepo.findById(customerId);
        if (customerData.isPresent()) {
            model.addAttribute("PageTitle", "Customers");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "customers");
            model.addAttribute("editCustomer", customerData.get());
            return "pages/customers/customersAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Customers Details Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/customers";
        }
    }

    @PostMapping(value = "/Add/customer")
    public String createCustomersAction(Customers customers, RedirectAttributes redirectAttributes) {
        try {
            customersRepo.save(new Customers(customers.getTpin(),customers.getFullName(),customers.getAddress(),customers.getContactNumber()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Customers Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/customers";
    }

    @PostMapping(value = "/Update/customer")
    public String updateCustomersAction(Customers customers, RedirectAttributes redirectAttributes){
        Optional<Customers> customerData = customersRepo.findById(customers.getId());
        if (customerData.isPresent()) {
            Customers _costumers = customerData.get();
            _costumers.setTpin(customers.getTpin());
            _costumers.setFullName(customers.getFullName());
            _costumers.setAddress(customers.getAddress());
            _costumers.setContactNumber(customers.getContactNumber());
            customersRepo.save(_costumers);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Customers Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Customer Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/customers";
    }

    @GetMapping("/Delete/customer/{customerId}")
    public String deleteCustomers(@PathVariable long customerId, RedirectAttributes redirectAttributes) {
        try {
            customersRepo.deleteById(customerId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Customer Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Customer Details");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/customers";
    }
}
