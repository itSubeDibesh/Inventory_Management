package com.itsubedibesh.walmart.controllers.web.Members.Vendors;

import com.itsubedibesh.walmart.controllers.api.Inventory.Products.Products;
import com.itsubedibesh.walmart.controllers.api.Inventory.Products.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/vendors")
public class VendorsWeb {
    @Autowired
    ProductsRepo productsRepo;
    @GetMapping()
    public String productsViewPage(final Model model){
        model.addAttribute("PageTitle", "Vendors");
        return "pages/vendors/vendors";
    }

    @GetMapping("/products/{vendorId}")
    public String updateCustomersViewPage(@PathVariable() long vendorId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<Products> vendorData = productsRepo.findById(vendorId);
        if (vendorData.isPresent()) {
            model.addAttribute("PageTitle", "Vendor");
            model.addAttribute("Action", "Products Listing");
            model.addAttribute("BaseLink", "vendors");
            model.addAttribute("vendorDetails", vendorData.get());
            return "pages/vendors/vendorsProducts";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Vendor Details Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/vendors";
        }
    }

}
