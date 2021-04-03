package com.itsubedibesh.walmart.controllers.web.Inventory.DamageAndExpiry;

import com.itsubedibesh.walmart.controllers.api.Inventory.DamageAndExpiry.DamageAndExpiry;
import com.itsubedibesh.walmart.controllers.api.Inventory.DamageAndExpiry.DamageAndExpiryRepo;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts.WarehouseAndMartProducts;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts.WarehouseAndMartProductsRepo;
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
@RequestMapping("/damage_and_expiry")
public class DamageAndExpiryWeb {
    @Autowired
    DamageAndExpiryRepo damageAndExpiryRepo;

    @Autowired
    WarehouseAndMartProductsRepo warehouseAndMartProductsRepo;

    @GetMapping()
    public String damageAndExpiryViewPage(final Model model) {
        model.addAttribute("PageTitle", "Damage And Expires");
        return "pages/damage_and_expiry/damage_and_expiry";
    }

    @GetMapping("/Add/damage_and_expiry")
    public String createDamageAndExpiryPage(final Model model) {
        model.addAttribute("PageTitle", "Damage And Expiry");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "damage_and_expiry");
        return "pages/damage_and_expiry/damage_and_expiryAddEdit";
    }

    @PostMapping(value = "/Add/damage_and_expiry")
    public String createDamageAndExpiryAction(DamageAndExpiryDto DamageAndExpiryDataSet, RedirectAttributes redirectAttributes) {
        try {
            Optional<WarehouseAndMartProducts> productId = warehouseAndMartProductsRepo.findById(DamageAndExpiryDataSet.getProductId());
            // Update Quantity Value in Warehouse and Mart Product Table
            if (productId.isPresent()){
                WarehouseAndMartProducts _products= productId.get();
                _products.setAvailableQuantity(_products.getAvailableQuantity()-DamageAndExpiryDataSet.getQuantity());
                warehouseAndMartProductsRepo.save(_products);
                // Add entry to Database
                damageAndExpiryRepo.save(new DamageAndExpiry(_products,DamageAndExpiryDataSet.getDamageAndExpiryEnum(),DamageAndExpiryDataSet.getQuantity()));
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Damage And Expiry Added Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");

            }else{
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Damage And Expiry Details Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/damage_and_expiry";
    }

    @GetMapping("/Delete/damage_and_expiry/{DamageAndExpiryId}")
    public String deleteDamageAndExpiry(@PathVariable Long DamageAndExpiryId, RedirectAttributes redirectAttributes) {
        try {
            damageAndExpiryRepo.deleteById(DamageAndExpiryId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Damage And Expiry Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Damage And Expiry Details");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/damage_and_expiry";
    }
}
