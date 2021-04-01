package com.itsubedibesh.walmart.controllers.web.Administartion.Authorities.Mappings.RoleAccess;

import com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Access.Access;
import com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Access.AccessRepo;
import com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Mappings.RoleAccessMap.RoleAccessMapping;
import com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Mappings.RoleAccessMap.RoleAccessMappingRepo;
import com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Roles.RoleRepo;
import com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Roles.Roles;
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
@RequestMapping("/login_role_mapping")
public class RoleAccessWeb {
    @Autowired
    RoleAccessMappingRepo roleAccessMappingRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    AccessRepo accessRepo;

    @GetMapping()
    public String login_role_mappingPage(final Model model) {
        model.addAttribute("PageTitle", "Login Roles Mapping");
        return "pages/login_role_mapping/login_role_mapping";
    }

    @GetMapping("/Add/roleAccess")
    public String createRoleAccessMappingViewPage(final Model model) {
        model.addAttribute("PageTitle", "Role Access Mapping");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "login_role_mapping");
        return "pages/login_role_mapping/roleAccessMappingAddEdit";
    }

    @GetMapping("/Update/roleAccess/{roleAccessId}")
    public String updateRoleAccessMappingViewPage(@PathVariable(required = true) Integer roleAccessId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<RoleAccessMapping> roleAccessDataSet = roleAccessMappingRepo.findById(roleAccessId);
        if (roleAccessDataSet.isPresent()) {
            model.addAttribute("PageTitle", "Role Access Mapping");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "login_role_mapping");
            model.addAttribute("editRoleAccess", roleAccessDataSet.get());
            return "pages/login_role_mapping/roleAccessMappingAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Access Map Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/login_role_mapping";
        }
    }


    @PostMapping(value = "/Add/roleAccess", consumes = "application/x-www-form-urlencoded")
    public String createRoleAccessMappingAction(RoleAccessDto roleAccessDto, RedirectAttributes redirectAttributes) {
        try {
            Optional<Roles> role = roleRepo.findById(roleAccessDto.getRoleId());
            Optional<Access> access = accessRepo.findById(roleAccessDto.getAccessId());
            roleAccessMappingRepo.save(new RoleAccessMapping(role.get(), access.get()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Access Mapped Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/login_role_mapping";
    }

    @PostMapping(value = "/Update/roleAccess", consumes = "application/x-www-form-urlencoded")
    public String updateRoleAccessMappingAction(RoleAccessDto roleAccessDto, RedirectAttributes redirectAttributes) {
        Optional<RoleAccessMapping> roleAccessMapping = roleAccessMappingRepo.findById(roleAccessDto.getId());
        if (roleAccessMapping.isPresent()) {
            Optional<Roles> role = roleRepo.findById(roleAccessDto.getRoleId());
            Optional<Access> access = accessRepo.findById(roleAccessDto.getAccessId());
            RoleAccessMapping _mapping = roleAccessMapping.get();
            _mapping.setAccessId(access.get());
            _mapping.setRoleId(role.get());
            roleAccessMappingRepo.save(_mapping);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Access Map Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Access Map Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/login_role_mapping";
    }

    @GetMapping("/Delete/roleAccess/{roleAccessId}")
    public String deleteRoleAccessMapping(@PathVariable Integer roleAccessId, RedirectAttributes redirectAttributes) {
        try {
            roleAccessMappingRepo.deleteById((Integer) roleAccessId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Role Access Map Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Role Access Map");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/login_role_mapping";
    }

}
