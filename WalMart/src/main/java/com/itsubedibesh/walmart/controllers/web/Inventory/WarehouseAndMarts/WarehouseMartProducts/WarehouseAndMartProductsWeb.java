package com.itsubedibesh.walmart.controllers.web.Inventory.WarehouseAndMarts.WarehouseMartProducts;

import com.itsubedibesh.walmart.controllers.api.Inventory.Products.Products;
import com.itsubedibesh.walmart.controllers.api.Inventory.Products.ProductsRepo;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart.WareHouseAndMart;
import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart.WarehouseAndMartRepo;
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
@RequestMapping("/products")
public class WarehouseAndMartProductsWeb {
    @Autowired
    WarehouseAndMartProductsRepo wAMPRepo;

    @Autowired
    WarehouseAndMartRepo wAMRepo;

    @Autowired
    ProductsRepo productsRepo;

    @GetMapping("/Add/WarehouseAndMartProduct")
    public String createWarehouseAndMartProductsViewPage(final Model model) {
        model.addAttribute("PageTitle", "Warehouse And Mart Product");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "products");
        return "pages/products/WarehouseAndMartProductsAddEdit";
    }

    @GetMapping("/Update/WarehouseAndMartProduct/{productId}")
    public String updateWarehouseAndMartProductsViewPage(@PathVariable() long productId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<WarehouseAndMartProducts> productData = wAMPRepo.findById(productId);
        if (productData.isPresent()) {
            model.addAttribute("PageTitle", "Warehouse And Mart Product");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "products");
            model.addAttribute("editWAMProduct", productData.get());
            return "pages/products/WarehouseAndMartProductsAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Mart Product Details Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/products";
        }
    }

    @PostMapping(value = "/Add/WarehouseAndMartProduct")
    public String createWarehouseAndMartProductsAction(WarehouseAndMartProductsDto products, RedirectAttributes redirectAttributes)  {
        try {
            Optional<Products> productId = productsRepo.findById(products.getProductId());
            Optional<WareHouseAndMart> WMId = wAMRepo.findById(products.getWMId());
            wAMPRepo.save(new WarehouseAndMartProducts(WMId.get(),productId.get(),products.getAvailableQuantity(),products.getSellingPrice()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Mart Product Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/products";
    }

    @PostMapping(value = "/Update/WarehouseAndMartProduct")
    public String updateWarehouseAndMartProductsAction(WarehouseAndMartProductsDto products, RedirectAttributes redirectAttributes){
        Optional<WarehouseAndMartProducts> productData = wAMPRepo.findById(products.getId());
        if (productData.isPresent()) {
            WarehouseAndMartProducts _products = productData.get();
            Optional<Products> productId = productsRepo.findById(products.getProductId());
            Optional<WareHouseAndMart> WMId = wAMRepo.findById(products.getWMId());
            _products.setProductId(productId.get());
            _products.setWMId(WMId.get());
            _products.setAvailableQuantity(products.getAvailableQuantity());
            _products.setSellingPrice(products.getSellingPrice());
            wAMPRepo.save(_products);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Mart Product Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "User Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/products";
    }

    @GetMapping("/Delete/WarehouseAndMartProduct/{productId}")
    public String deleteWarehouseAndMartProducts(@PathVariable long productId, RedirectAttributes redirectAttributes) {
        try {
            wAMPRepo.deleteById(productId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Warehouse And Mart Product Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Warehouse And Mart Product Details");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/products";
    }
}
