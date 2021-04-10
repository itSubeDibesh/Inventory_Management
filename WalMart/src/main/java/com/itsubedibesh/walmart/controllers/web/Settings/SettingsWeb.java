package com.itsubedibesh.walmart.controllers.web.Settings;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.LoginsRepo;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.Users;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.UsersRepo;
import com.itsubedibesh.walmart.controllers.web.Administartion.Users.Users.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/settings")
public class SettingsWeb {

    @Autowired
    LoginsRepo loginsRepo;

    @Autowired
    UsersRepo usersRepo;

    @GetMapping
    public String settingsViewPage(final Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Logins> logins = loginsRepo.findById(loggedUser.getId());
            Optional<Users> users = usersRepo.findByLoginId(loggedUser);
            model.addAttribute("PageTitle", "Settings");
            model.addAttribute("editSettingsLogin", logins.get());
            model.addAttribute("editSettingsUser", users.get());
            return "pages/settings/settings";
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
            return "redirect:/logout";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/Update/user")
    public String updateUserAction(UsersDto users, RedirectAttributes redirectAttributes, HttpSession session) {
        Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
        if (loggedUser != null) {
            Optional<Users> userData = usersRepo.findById(users.getId());
            if (userData.isPresent()) {
                Users _user = userData.get();
                Optional<Logins> loggedIn = loginsRepo.findById(users.getLoginId());
                _user.setLoginId(loggedIn.get());
                _user.setFullName(users.getFullName());
                _user.setContactNumber(users.getContactNumber());
                _user.setAddress(users.getAddress());
                _user.setDob(users.getDob());
                _user.setGender(users.getGender());
                _user.setTpin(users.getTpin());
                usersRepo.save(_user);
                redirectAttributes.addFlashAttribute("noticeTitle", "Success");
                redirectAttributes.addFlashAttribute("noticeMessage", "User Updated Successfully");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Error");
                redirectAttributes.addFlashAttribute("noticeMessage", "User Not Found");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            }
            return "redirect:/settings";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Un-authorized");
            redirectAttributes.addFlashAttribute("noticeMessage", "Unauthorized Access!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        }
    }

}
