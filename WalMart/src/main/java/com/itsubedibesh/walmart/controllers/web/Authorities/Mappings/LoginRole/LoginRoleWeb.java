package com.itsubedibesh.walmart.controllers.web.Authorities.Mappings.LoginRole;

import com.itsubedibesh.walmart.controllers.api.Authorities.Mappings.LoginRoleMap.LoginRoleMapping;
import com.itsubedibesh.walmart.controllers.api.Authorities.Mappings.LoginRoleMap.LoginRoleMappingRepo;
import com.itsubedibesh.walmart.controllers.api.Authorities.Roles.RoleRepo;
import com.itsubedibesh.walmart.controllers.api.Authorities.Roles.Roles;
import com.itsubedibesh.walmart.controllers.api.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Users.Logins.LoginsRepo;
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
public class LoginRoleWeb {

    @Autowired
    LoginRoleMappingRepo loginRoleMappingRepo;

    @Autowired
    LoginsRepo loginsRepo;

    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/Add/loginRole")
    public String createLoginRoleMappingViewPage(final Model model) {
        model.addAttribute("PageTitle", "Login Role Mapping");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "login_role_mapping");
        return "pages/login_role_mapping/loginRoleMappingAddEdit";
    }

    @GetMapping("/Update/loginRole/{loginRoleId}")
    public String updateLoginRoleMappingViewPage(@PathVariable(required = true) Integer loginRoleId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<LoginRoleMapping> loginRoleDataSet = loginRoleMappingRepo.findById(loginRoleId);
        if (loginRoleDataSet.isPresent()) {
            model.addAttribute("PageTitle", "Login Role Mapping");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "login_role_mapping");
            model.addAttribute("editLoginRole", loginRoleDataSet.get());
            return "pages/login_role_mapping/loginRoleMappingAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Role Map Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/login_role_mapping";
        }
    }


    @PostMapping(value = "/Add/loginRole", consumes = "application/x-www-form-urlencoded")
    public String createLoginRoleMappingAction(LoginRoleDto loginRoleDto, RedirectAttributes redirectAttributes) {
        try {
            Optional<Roles> role = roleRepo.findById(loginRoleDto.getRoleId());
            Optional<Logins> logins = loginsRepo.findById(loginRoleDto.getLoginId());
            loginRoleMappingRepo.save(new LoginRoleMapping(logins.get(),role.get()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Role Mapped Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/login_role_mapping";
    }

    @PostMapping(value = "/Update/loginRole", consumes = "application/x-www-form-urlencoded")
    public String updateLoginRoleMappingAction(LoginRoleDto loginRoleDto, RedirectAttributes redirectAttributes) {
        Optional<LoginRoleMapping> loginRoleData = loginRoleMappingRepo.findById(loginRoleDto.getId());
        if (loginRoleData.isPresent()) {
            Optional<Roles> role = roleRepo.findById(loginRoleDto.getRoleId());
            Optional<Logins> logins = loginsRepo.findById(loginRoleDto.getLoginId());
            LoginRoleMapping _mapping = loginRoleData.get();
            _mapping.setLoginId(logins.get());
            _mapping.setRoleId(role.get());
            loginRoleMappingRepo.save(_mapping);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Role Map Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Role Map Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/login_role_mapping";
    }

    @GetMapping("/Delete/loginRole/{loginRoleId}")
    public String deleteLoginRoleMapping(@PathVariable Integer roleAccessId, RedirectAttributes redirectAttributes) {
        try {
            loginRoleMappingRepo.deleteById((Integer) roleAccessId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Role Map Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Login Role Map");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/login_role_mapping";
    }


}
