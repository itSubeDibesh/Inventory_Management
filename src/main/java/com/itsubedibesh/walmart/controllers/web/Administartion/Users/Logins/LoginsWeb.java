package com.itsubedibesh.walmart.controllers.web.Administartion.Users.Logins;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.LoginsRepo;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.itsubedibesh.walmart.controllers.configuration.FileUploader.FileUploader.saveFile;
import static com.itsubedibesh.walmart.controllers.configuration.FileUploader.FileUploader.updateFile;

@Controller
@RequestMapping("/users")
public class LoginsWeb {

    @Autowired
    LoginsRepo loginsRepo;

    @GetMapping()
    public String usersPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Users");
            return "pages/users/users";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Add/login")
    public String createLoginViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            model.addAttribute("PageTitle", "Logins");
            model.addAttribute("Action", "Add");
            model.addAttribute("BaseLink", "users");
            return "pages/users/userLoginsAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Update/login/{loginId}")
    public String updateLoginViewPage(@PathVariable(required = true) long loginId, final Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
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
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Add/login")
    public String createLoginAction(Logins logins, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            try {
                String uploadDir = "src/User_Images/" + logins.getEmail().toLowerCase();
                String avatarName;
                avatarName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                if (avatarName.isEmpty()) {
                    avatarName = null;
                } else {
                    FileUploader.saveFile(uploadDir, avatarName, multipartFile);
                }
                loginsRepo.save(new Logins(logins.getPhone(), logins.getEmail(), avatarName, logins.getUserName(), logins.getPassword()));
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Login Added Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/users";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Update/login")
    public String updateLoginAction(Logins logins, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Logins> loginData = loginsRepo.findById(logins.getId());
            if (loginData.isPresent()) {
                String uploadDir = "src/User_Images/" + logins.getEmail().toLowerCase();
                String avatarName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                Logins _login = loginData.get();
                _login.setUserName(logins.getUserName());
                _login.setPassword(logins.getPassword());
                _login.setPhone(logins.getPhone());
                _login.setEmail(logins.getEmail());
                if (avatarName.isEmpty()) {
                    _login.setAvatar(logins.getAvatar());
                } else {
                    _login.setAvatar(avatarName);
                    Path uploadPath = Paths.get(uploadDir + "/" + avatarName);
                    if (Files.exists(uploadPath)) {
                        updateFile(uploadDir, logins.getImagePath(), avatarName, multipartFile);
                    } else {
                        saveFile(uploadDir, avatarName, multipartFile);
                    }
                }
                loginsRepo.save(_login);
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "Login Updated Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Login Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/users";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @GetMapping("/Delete/login/{loginId}")
    public String deleteLogin(@PathVariable long loginId, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Logins> loginData = loginsRepo.findById(loginId);
            if (loginData.isPresent()) {
                Logins _login = loginData.get();
                try {
                    loginsRepo.deleteById((long) loginId);
                    FileUploader.deleteDirectory("src/User_Images/" + _login.getEmail().toLowerCase());
                    redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Login Deleted Successfully");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                    redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting Login Details");
                    redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
                }
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "Login Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/users";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

}
