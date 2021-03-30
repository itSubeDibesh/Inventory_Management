package com.itsubedibesh.walmart.controllers.web.Authorization;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthorizationController {

    @GetMapping("/")
    public String home(){
        return "redirect:/dashboard";
    }

    @GetMapping("login")
    public String loginPage(final Model model){
        model.addAttribute("PageTitle","Login");
        return "pages/login";
    }

//    Create Post Method for session validation and user login

//    Create Logout

}
