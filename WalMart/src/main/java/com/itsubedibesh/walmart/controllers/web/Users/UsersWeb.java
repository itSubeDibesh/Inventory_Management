package com.itsubedibesh.walmart.controllers.web.Users;

import com.itsubedibesh.walmart.controllers.api.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Users.Logins.LoginsRepo;
import com.itsubedibesh.walmart.controllers.configuration.FileUploader.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersWeb {

    @Autowired
    LoginsRepo loginsRepo;

    @GetMapping()
    public String usersPage(final Model model) {
        model.addAttribute("PageTitle", "Users");
        return "pages/users/users";
    }

    @GetMapping("/Add/login")
    public String createLoginViewPage(final Model model) {
        model.addAttribute("PageTitle", "Logins");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "users");
        return "pages/users/userLoginsAddEdit";
    }

    @GetMapping("/Update/login/{loginId}")
    public String updateLoginViewPage(@PathVariable(required = true) long loginId, final Model model, RedirectAttributes redirectAttributes) {
        // Fetch Data By LoginId Id
        Optional<Logins> loginData = loginsRepo.findById(loginId);
        if (loginData.isPresent()) {
            model.addAttribute("PageTitle", "Logins");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "users");
            model.addAttribute("editLogin", loginData.get());
            return "pages/users/userLoginsAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Details Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/users";
        }
    }

    @PostMapping(value = "/Add/login")
    public String createLoginAction(Logins logins, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
        try {
            String uploadDir = "src/User_Images/"+logins.getEmail().toLowerCase() ;
            String avatarName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            loginsRepo.save(new Logins(logins.getPhone(), logins.getEmail(), avatarName, logins.getUserName(), logins.getPassword()));
            FileUploader.saveFile(uploadDir,avatarName,multipartFile);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/users";
    }
    @PostMapping(value = "/Update/login")
    public String updateLoginAction(Logins logins, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
        Optional<Logins> loginData = loginsRepo.findById(logins.getId());
        if(loginData.isPresent()){
            String uploadDir = "src/User_Images/"+logins.getEmail().toLowerCase() ;
            String avatarName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Logins _login = loginData.get();
            _login.setUserName(logins.getUserName());
            _login.setPassword(logins.getPassword());
            _login.setPhone(logins.getPhone());
            _login.setEmail(logins.getEmail());
            _login.setAvatar(avatarName);
            loginsRepo.save(_login);
            FileUploader.updateFile(uploadDir,logins.getImagePath(),avatarName,multipartFile);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Updated Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        }else{
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/users";
    }

    @GetMapping("/Delete/login/{loginId}")
    public String deleteLogin(@PathVariable long loginId, RedirectAttributes redirectAttributes) {
        Optional<Logins> loginData = loginsRepo.findById(loginId);
        if(loginData.isPresent()){
            Logins _login = loginData.get();
            try {
                loginsRepo.deleteById((long) loginId);
                FileUploader.deleteDirectory( "src/User_Images/"+_login.getEmail().toLowerCase());
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Login Deleted Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Login Details");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
        }else{
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Login Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/users";
    }

}
