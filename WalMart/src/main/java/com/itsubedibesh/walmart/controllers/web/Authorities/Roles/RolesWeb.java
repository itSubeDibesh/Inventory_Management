package com.itsubedibesh.walmart.controllers.web.Authorities.Roles;

import com.itsubedibesh.walmart.controllers.api.Authorities.Roles.RoleRepo;
import com.itsubedibesh.walmart.controllers.api.Authorities.Roles.Roles;
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
@RequestMapping("roles_and_access")
public class RolesWeb {

    @Autowired
    RoleRepo roleRepo;

    @GetMapping()
    public String roles_and_accessPage(final Model model){
        model.addAttribute("PageTitle", "Roles and Access");
        return "pages/roles_and_access/roles_and_access";
    }

    @GetMapping("/Add/role")
    public String createRoleViewPage(final Model model) {
        model.addAttribute("PageTitle", "Role");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "roles_and_access");
        return "/pages/roles_and_access/rolesAddEdit";
    }

    @GetMapping("/Update/role/{roleId}")
    public String updateRoleViewPage(@PathVariable(required = true) Integer roleId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<Roles> roleData = roleRepo.findById(roleId);
        if (roleData.isPresent()) {
            model.addAttribute("PageTitle", "Role");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "roles_and_access");
            model.addAttribute("editRole", roleData.get());
            return "/pages/roles_and_access/rolesAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Details Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/roles_and_access";
        }
    }

    @PostMapping(value = "/Add/role",consumes = "application/x-www-form-urlencoded")
    public String createRoleAction(Roles roles, RedirectAttributes redirectAttributes) {
        try {
            roleRepo.save(new Roles(roles.getName(), roles.getDescription()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/roles_and_access";
    }

    @PostMapping(value = "/Update/role", consumes = "application/x-www-form-urlencoded")
    public String updateRoleAction(Roles roles, RedirectAttributes redirectAttributes) {
        Optional<Roles> roleData = roleRepo.findById(roles.getId());
        if (roleData.isPresent()) {
            Roles _roles = roleData.get();
            _roles.setName(roles.getName());
            _roles.setDescription(roles.getDescription());
            roleRepo.save(_roles);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/roles_and_access";
    }

    @GetMapping("/Delete/role/{roleId}")
    public String deleteRole(@PathVariable Integer roleId, RedirectAttributes redirectAttributes) {
        try {
            roleRepo.deleteById((Integer) roleId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Role Details");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/roles_and_access";
    }

}
