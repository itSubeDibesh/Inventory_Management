package com.itsubedibesh.walmart.controllers.web.Inventory.Products;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Inventory.Categories.Categories;
import com.itsubedibesh.walmart.controllers.api.Inventory.Categories.CategoriesRepo;
import com.itsubedibesh.walmart.controllers.api.Inventory.Products.Products;
import com.itsubedibesh.walmart.controllers.api.Inventory.Products.ProductsRepo;
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
@RequestMapping("/products")
public class ProductWeb {

    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    CategoriesRepo categoriesRepo;

    @GetMapping()
    public String productsViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Products");
            return "pages/products/products";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Add/product")
    public String createProductViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Products");
            model.addAttribute("Action", "Add");
            model.addAttribute("BaseLink", "products");
            return "pages/products/productsAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Update/product/{productId}")
    public String updateProductViewPage(@PathVariable() long productId, final Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            // Fetch Data By LoginId Id
            Optional<Products> productData = productsRepo.findById(productId);
            if (productData.isPresent()) {
                model.addAttribute("PageTitle", "Products");
                model.addAttribute("Action", "Update");
                model.addAttribute("BaseLink", "products");
                model.addAttribute("editProduct", productData.get());
                return "pages/products/productsAddEdit";
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
                redirectAttributes.addFlashAttribute("noticeMessage", "Product Details Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
                return "redirect:/products";
            }
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Add/product")
    public String createProductAction(ProductDto products, @RequestParam("image") MultipartFile multipartFile, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            try {
                String uploadDir = "src/Product_Images/";
                String imageName;
                imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                Optional<Categories> categoryId = categoriesRepo.findById(products.getCategoryId());
                productsRepo.save(new Products(categoryId.get(), products.getProductName(), products.getVendorName(), products.getVendorContact(), imageName, products.getInvoiceNumber(), products.getInvoiceDate(), products.getPurchasedQuantity(), products.getPurchasedPrice()));
                if (imageName.isEmpty()) {
                    imageName = null;
                } else {
                    FileUploader.saveFile(uploadDir, imageName, multipartFile);
                }
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Product Added Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/products";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Update/product")
    public String updateProductAction(ProductDto products, @RequestParam("image") MultipartFile multipartFile, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Products> productData = productsRepo.findById(products.getId());
            if (productData.isPresent()) {
                String uploadDir = "src/Product_Images/";
                String imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                Products _products = productData.get();
                Optional<Categories> categoryId = categoriesRepo.findById(products.getCategoryId());
                _products.setCategoryId(categoryId.get());
                _products.setProductName(products.getProductName());
                _products.setVendorName(products.getVendorName());
                _products.setVendorContact(products.getVendorContact());
                _products.setInvoiceNumber(products.getInvoiceNumber());
                _products.setInvoiceDate(products.getInvoiceDate());
                _products.setPurchasedPrice(products.getPurchasedPrice());
                _products.setPurchasedQuantity(products.getPurchasedQuantity());

                if (imageName.isEmpty()) {
                    _products.setProductImage(products.getProductImage());
                } else {
                    _products.setProductImage(imageName);
                    FileUploader.updateFile(uploadDir, products.getImagePath(), imageName, multipartFile);
                }
                productsRepo.save(_products);
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Product Updated Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Product Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/products";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Delete/product/{productId}")
    public String deleteProduct(@PathVariable long productId, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Products> productData = productsRepo.findById(productId);
            if (productData.isPresent()) {
                Products _products = productData.get();
                try {
                    FileUploader.deleteFile(_products.getImagePath());
                    productsRepo.deleteById(productId);
                    redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Product Deleted Successfully");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Product Details");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
                }
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Product Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/products";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }
}
