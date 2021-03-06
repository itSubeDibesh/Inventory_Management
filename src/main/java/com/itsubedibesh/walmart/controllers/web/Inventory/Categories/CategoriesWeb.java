package com.itsubedibesh.walmart.controllers.web.Inventory.Categories;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Inventory.Categories.Categories;
import com.itsubedibesh.walmart.controllers.api.Inventory.Categories.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoriesWeb {

    @Autowired
    CategoriesRepo categoriesRepo;

    @GetMapping()
    public String categoriesPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Categories");
            return "pages/categories/categories";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Update/category/{categoryId}")
    public String updateCategoryViewPage(@PathVariable() long categoryId, final Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
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
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Add/category")
    public String createCategoryViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Category");
            model.addAttribute("Action", "Add");
            model.addAttribute("BaseLink", "categories");
            return "pages/categories/categoryAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Add/category", consumes = "application/x-www-form-urlencoded")
    public String createCategoryAction(CategoriesDto categories, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
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
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Update/category", consumes = "application/x-www-form-urlencoded")
    public String updateCategoryAction(CategoriesDto categories, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
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
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Delete/category/{categoryId}")
    public String deleteCategory(@PathVariable long categoryId, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
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
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Update/subCategory/{categoryId}")
    public String updateSubCategoryViewPage(@PathVariable(required = true) long categoryId, final Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
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
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Add/subCategory")
    public String createSubCategoryViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Sub Category");
            model.addAttribute("Action", "Add");
            model.addAttribute("BaseLink", "categories");
            return "pages/categories/subCategoryAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Add/subCategory", consumes = "application/x-www-form-urlencoded")
    public String createSubCategoryAction(SubCategoryDto subCategory, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            // Fetching Category
            Optional<Categories> categoryData = categoriesRepo.findById(subCategory.getCategoryId());
            Optional<Categories> parentData = categoriesRepo.findById(subCategory.getParentId());
            if (categoryData.isPresent()) {
                if (!categoryData.get().equals(parentData.get())) {
                    Categories _category = categoryData.get();
                    _category.setParentId(parentData.get());
                    categoriesRepo.save(_category);
                    redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Sub Category Added Successfully");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
                } else {
                    redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Invalid Combination");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
                }
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Something Went Wrong");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/categories";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Update/subCategory", consumes = "application/x-www-form-urlencoded")
    public String updateSubCategoryAction(SubCategoryDto subCategory, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Categories> categoryData = categoriesRepo.findById(subCategory.getCategoryId());
            Optional<Categories> parentData = categoriesRepo.findById(subCategory.getParentId());
            if (categoryData.isPresent()) {
                if (!categoryData.get().equals(parentData.get())) {
                    Categories _category = categoryData.get();
                    _category.setParentId(parentData.get());
                    categoriesRepo.save(_category);
                    redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Sub Category Updated Successfully");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
                } else {
                    redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Invalid Combination");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
                }
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Something Went Wrong");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/categories";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Delete/subCategory/{categoryId}")
    public String deleteSubCategory(@PathVariable long categoryId, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Categories> categoryData = categoriesRepo.findById(categoryId);
            if (categoryData.isPresent()) {
                Categories _category = categoryData.get();
                _category.setParentId(null);
                categoriesRepo.save(_category);
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Sub Category Deleted Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Sub Category");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/categories";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }
}
