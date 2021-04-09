package com.itsubedibesh.walmart.controllers.web.RedirectHandel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/redirect")
public class Redirect {
    @GetMapping("/{title}/{message}/{bg}/{page}")
    public String redirectPage(@PathVariable() String title, @PathVariable() String message, @PathVariable() String bg, @PathVariable() String page, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("noticeTitle", title);
        redirectAttributes.addFlashAttribute("noticeMessage", message);
        redirectAttributes.addFlashAttribute("noticeBg", bg);
        return "redirect:/" + page;
    }
}
