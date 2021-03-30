package com.itsubedibesh.walmart.controllers.web.Categories;

import com.itsubedibesh.walmart.controllers.api.Categories.Categories;
import com.itsubedibesh.walmart.controllers.api.Categories.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoriesWeb {

    @Autowired
    CategoriesRepo categoriesRepo;

    @GetMapping()
    public String categoriesPage(final Model model) {
        model.addAttribute("PageTitle", "Categories");
        return "pages/categories/categories";
    }

    @GetMapping("/Update/category/{categoryId}")
    public String updateCategoryViewPage(@PathVariable(required = true) long categoryId, final Model model,RedirectAttributes redirectAttributes) {
        // Fetch Data By Category Id
        Optional<Categories> categoryData = categoriesRepo.findById(categoryId);
        if (categoryData.isPresent()) {
            model.addAttribute("PageTitle", "Category");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "categories");
            model.addAttribute("editCategory", categoryData.get());
            return "pages/categories/categoryAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Category Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/categories";
        }
    }

    @GetMapping("/Add/category")
    public String createCategoryViewPage(final Model model) {
        model.addAttribute("PageTitle", "Category");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "categories");
        return "pages/categories/categoryAddEdit";
    }

    @PostMapping(value = "/Add/category", consumes = "application/x-www-form-urlencoded")
    public String createCategoryAction(@Valid CategoriesDto categories, RedirectAttributes redirectAttributes) {
        try {
           categoriesRepo.save(new Categories(categories.getName(), categories.getDescription()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Category Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/categories";
    }

    @PostMapping(value = "/Update/category", consumes = "application/x-www-form-urlencoded")
    public String updateCategoryAction(@Valid CategoriesDto categories, RedirectAttributes redirectAttributes) {
        Optional<Categories> categoryData = categoriesRepo.findById(categories.getId());
        if (categoryData.isPresent()) {
            Categories _category = categoryData.get();
            _category.setName(categories.getName());
            _category.setDescription(categories.getDescription());
            categoriesRepo.save(_category);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Category Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Category Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/categories";
    }

    @GetMapping("/Delete/category/{categoryId}")
    public String deleteCategory(@PathVariable long categoryId, RedirectAttributes redirectAttributes) {
        try {
            categoriesRepo.deleteById((long) categoryId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Category Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Category");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/categories";
    }

    @GetMapping("/Update/subCategory/{categoryId}")
    public String updateSubCategoryViewPage(@PathVariable(required = true) long categoryId, final Model model,RedirectAttributes redirectAttributes) {
        // Fetch Data By Category Id
        Optional<Categories> categoryData = categoriesRepo.findById(categoryId);
        if (categoryData.isPresent()) {
            model.addAttribute("PageTitle", "Sub Category");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "categories");
            model.addAttribute("editCategory", categoryData.get());
            return "pages/categories/subCategoryAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Category Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/categories";
        }
    }

    @GetMapping("/Add/subCategory")
    public String createSubCategoryViewPage(final Model model) {
        model.addAttribute("PageTitle", "Sub Category");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "categories");
        return "pages/categories/subCategoryAddEdit";
    }

    @PostMapping(value = "/Add/subCategory", consumes = "application/x-www-form-urlencoded")
    public String createSubCategoryAction(@Valid SubCategoryDto subCategory, RedirectAttributes redirectAttributes) {
        // Fetching Category
        Optional<Categories> categoryData = categoriesRepo.findById(subCategory.getCategoryId());
        Optional<Categories> parentData = categoriesRepo.findById(subCategory.getParentId());
        if(categoryData.isPresent()){
            Categories _category = categoryData.get();
            _category.setParentId(parentData.get());
            categoriesRepo.save(_category);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Sub Category Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Something Went Wrong");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/categories";
    }

    @PostMapping(value = "/Update/subCategory", consumes = "application/x-www-form-urlencoded")
    public String updateSubCategoryAction(@Valid SubCategoryDto subCategory, RedirectAttributes redirectAttributes) {
        Optional<Categories> categoryData = categoriesRepo.findById(subCategory.getCategoryId());
        Optional<Categories> parentData = categoriesRepo.findById(subCategory.getParentId());
        if(categoryData.isPresent()){
            Categories _category = categoryData.get();
            _category.setParentId(parentData.get());
            categoriesRepo.save(_category);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Sub Category Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Something Went Wrong");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/categories";
    }

    @GetMapping("/Delete/subCategory/{categoryId}")
    public String deleteSubCategory(@PathVariable long categoryId, RedirectAttributes redirectAttributes) {
        Optional<Categories> categoryData = categoriesRepo.findById(categoryId);
        if(categoryData.isPresent()) {
            Categories _category = categoryData.get();
            _category.setParentId(null);
            categoriesRepo.save(_category);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Sub Category Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        }
        else{
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Sub Category");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/categories";
    }
}
