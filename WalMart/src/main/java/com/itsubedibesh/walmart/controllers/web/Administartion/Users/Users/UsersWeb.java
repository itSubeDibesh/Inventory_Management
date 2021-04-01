package com.itsubedibesh.walmart.controllers.web.Administartion.Users.Users;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.LoginsRepo;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.Users;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users.UsersRepo;
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
@RequestMapping("/users")
public class UsersWeb {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LoginsRepo loginsRepo;

    @GetMapping("/Add/user")
    public String createUserViewPage(final Model model) {
        model.addAttribute("PageTitle", "Users");
        model.addAttribute("Action", "Add");
        model.addAttribute("BaseLink", "users");
        return "pages/users/userAddEdit";
    }

    @GetMapping("/Update/user/{userId}")
    public String updateUserViewPage(@PathVariable() long userId, final Model model, RedirectAttributes redirectAttributes) {
        Optional<Users> userData = usersRepo.findById(userId);
        if (userData.isPresent()) {
            model.addAttribute("PageTitle", "Users");
            model.addAttribute("Action", "Update");
            model.addAttribute("BaseLink", "users");
            model.addAttribute("editUser", userData.get());
            return "pages/users/userAddEdit";
        } else {
            redirectAttributes.addFlashAttribute("noticeTitle", "Not Found");
            redirectAttributes.addFlashAttribute("noticeMessage", "User Details Not Found");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
            return "redirect:/users";
        }
    }

    @PostMapping(value = "/Add/user")
    public String createUserAction(UsersDto users, RedirectAttributes redirectAttributes) {
        try {
            Optional<Logins> loggedIn = loginsRepo.findById(users.getLoginId());
            usersRepo.save(new Users(loggedIn.get(),users.getFullName(),users.getContactNumber(),users.getAddress(),users.getDob(),users.getGender(),users.getTpin()));
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "User Added Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/users";
    }

    @PostMapping(value = "/Update/user")
    public String updateUserAction(UsersDto users, RedirectAttributes redirectAttributes){
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
        return "redirect:/users";
    }

    @GetMapping("/Delete/user/{userId}")
    public String deleteUser(@PathVariable long userId, RedirectAttributes redirectAttributes) {
        try {
            usersRepo.deleteById(userId);
            redirectAttributes.addFlashAttribute("noticeTitle", "Success");
            redirectAttributes.addFlashAttribute("noticeMessage", "User Deleted Successfully");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("noticeTitle", "Error");
            redirectAttributes.addFlashAttribute("noticeMessage", "Problem Deleting User Details");
            redirectAttributes.addFlashAttribute("noticeBg", "bg-danger");
        }
        return "redirect:/users";
    }
}
