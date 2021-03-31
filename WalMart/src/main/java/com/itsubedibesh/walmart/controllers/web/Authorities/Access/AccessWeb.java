package com.itsubedibesh.walmart.controllers.web.Authorities.Access;

import com.itsubedibesh.walmart.controllers.api.Authorities.Access.Access;
import com.itsubedibesh.walmart.controllers.api.Authorities.Access.AccessRepo;
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
public class AccessWeb {

    @Autowired
    AccessRepo accessRepo;

    @GetMapping("/Add/access")
    public String createAccessViewPage(final Model model) {
        model.addAttribute("PageTitle", "Access");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "roles_and_access");
        return "/pages/roles_and_access/accessAddEdit";
    }

    @GetMapping("/Update/access/{accessId}")
    public String updateAccessViewPage(@PathVariable(required = true) Integer accessId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<Access> accessData = accessRepo.findById(accessId);
        if (accessData.isPresent()) {
            model.addAttribute("PageTitle", "Access");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "roles_and_access");
            model.addAttribute("editAccess", accessData.get());
            return "/pages/roles_and_access/accessAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Access Details Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/roles_and_access";
        }
    }

    @PostMapping(value = "/Add/access",consumes = "application/x-www-form-urlencoded")
    public String createAccessAction(Access access, RedirectAttributes redirectAttributes) {
        try {
            accessRepo.save(new Access(access.getName(),access.getCreateAccess(),access.getReadAccess(),access.getUpdateAccess(),access.getDeleteAccess(), access.getDescription()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Access Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/roles_and_access";
    }

    @PostMapping(value = "/Update/access", consumes = "application/x-www-form-urlencoded")
    public String updateAccessAction(Access access, RedirectAttributes redirectAttributes) {
        Optional<Access> accessData = accessRepo.findById(access.getId());
        if (accessData.isPresent()) {
            Access _access = accessData.get();
            _access.setName(access.getName());
            _access.setCreateAccess(access.getCreateAccess());
            _access.setReadAccess(access.getReadAccess());
            _access.setUpdateAccess(access.getUpdateAccess());
            _access.setDeleteAccess(access.getDeleteAccess());
            _access.setDescription(access.getDescription());
            accessRepo.save(_access);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Access Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Access Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/roles_and_access";
    }

    @GetMapping("/Delete/access/{accessId}")
    public String deleteAccess(@PathVariable Integer accessId, RedirectAttributes redirectAttributes) {
        try {
            accessRepo.deleteById((Integer) accessId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Access Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Access Details");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/roles_and_access";
    }
}
