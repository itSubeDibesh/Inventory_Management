package com.itsubedibesh.walmart.controllers.web.Inventory.WarehouseAndMarts.WarehouseAndMart;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.Users;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart.WareHouseAndMart;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart.WarehouseAndMartRepo;
import com.itsubedibesh.walmart.controllers.configuration.FileUploader.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("warehouse_and_marts")
public class WarehouseAndMartWeb {
    @Autowired
    WarehouseAndMartRepo WmRepo;

    @GetMapping()
    public String warehouseMartViewPage(final Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Warehouse And Marts");
            return "pages/warehouse_and_marts/warehouse_and_marts";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Add/warehouseMart")
    public String createWarehouseMartViewPage(final Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Warehouse And Marts");
            model.addAttribute("Action", "Add");
            model.addAttribute("BaseLink", "warehouse_and_marts");
            return "pages/warehouse_and_marts/warehouse_and_martsAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Update/warehouseMart/{WORMId}")
    public String updateWarehouseMartViewPage(@PathVariable() Integer WORMId, final Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<WareHouseAndMart> wormData = WmRepo.findById(WORMId);
            if (wormData.isPresent()) {
                model.addAttribute("PageTitle", "Warehouse And Marts");
                model.addAttribute("Action", "Update");
                model.addAttribute("BaseLink", "warehouse_and_marts");
                model.addAttribute("editWORM", wormData.get());
                return "pages/warehouse_and_marts/warehouse_and_martsAddEdit";
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
                redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Marts Details Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
                return "redirect:/warehouse_and_marts";
            }
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Add/warehouseMart")
    public String createWarehouseMartAction(WareHouseAndMart WORMData, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            try {
                WmRepo.save(new WareHouseAndMart(WORMData.getName(), WORMData.getType(), WORMData.getAddress(), WORMData.getContact(), WORMData.getDescription()));
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Marts Added Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/warehouse_and_marts";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Update/warehouseMart")
    public String updateWarehouseMartAction(WareHouseAndMart WORMData, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<WareHouseAndMart> wormData = WmRepo.findById(WORMData.getId());
            if (wormData.isPresent()) {
                WareHouseAndMart _worm = wormData.get();
                _worm.setName(WORMData.getName());
                _worm.setType(WORMData.getType());
                _worm.setAddress(WORMData.getAddress());
                _worm.setContact(WORMData.getContact());
                _worm.setDescription(WORMData.getDescription());
                WmRepo.save(_worm);
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Marts Updated Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Marts Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/warehouse_and_marts";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Delete/warehouseMart/{WORMId}")
    public String deleteWarehouseMart(@PathVariable Integer WORMId, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            try {
                WmRepo.deleteById(WORMId);
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Marts Deleted Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Warehouse And Marts Details");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/warehouse_and_marts";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }
}
