package com.itsubedibesh.walmart.controllers.web.Administartion.Authorization;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.LoginsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AuthorizationController {

    @Autowired
    LoginsRepo loginsRepo;

    @GetMapping("/")
    public String loginPage(final Model model, HttpSession session,RedirectAttributes redirectAttributes) {
        model.addAttribute("PageTitle", "Login");
        if (session.getAttribute("LoginDetails") != null) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Welcome Back");
            Logins loggedUser = (Logins) session.getAttribute("LoginDetails");
            redirectAttributes.addFlashAttribute("noticeMessage", "Welcome Back "+loggedUser.getUserName()+"!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
            return "redirect:/dashboard";
        } else
            return "pages/login";
    }

    @GetMapping("/resetPassword")
    public String resetPasswordPage(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("noticeTitle", "Service Restricted");
        redirectAttributes.addFlashAttribute("noticeMessage", "Please Contact Administrator!");
        redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        return "redirect:/";
    }

    //Create Post Method for session validation and user login
    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public String loginAction(AuthorizationDto auth, HttpSession session, RedirectAttributes redirectAttributes) {
        //Redirect With Message Username or password is required
        if (auth.getPassword().isEmpty() || auth.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Credentials Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "Username or Password is Missing!");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/";
        } else {
            //Check User From Database
            Optional<Logins> cred = loginsRepo.findLoginsByUserNameAndPassword(auth.getUserName(), auth.getPassword());
            //User Exists
            if (cred.isPresent()) {
                // Remove Existing Session
                session.removeAttribute("LoginDetails");
                //Set User Information in Session If User Exists
                session.setAttribute("LoginDetails", cred.get());
                //Redirect to Dashboard
                return "redirect:/dashboard";

            } else {
                redirectAttributes.addFlashAttribute("noticeTitle", "Invalid Credentials");
                redirectAttributes.addFlashAttribute("noticeMessage", "Invalid Username or Password!");
                redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
                return "redirect:/";
            }
        }
    }

    //    Create Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("LoginDetails");
        session.invalidate();
        return "redirect:/";
    }

}
