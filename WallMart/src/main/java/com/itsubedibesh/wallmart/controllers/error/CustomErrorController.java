package com.itsubedibesh.wallmart.controllers.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping("error")
    public String handleError(HttpServletRequest request, final Model model) {
        // get error status
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMsg = "";
        int errorCode = 500;

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            errorCode = statusCode;
            switch (statusCode) {
                case 400:
                    errorMsg = "It's A Bad Request";
                    model.addAttribute("PageTitle", "400 Error");
                    break;
                case 401:
                    model.addAttribute("PageTitle", "401 Error");
                    errorMsg = "Looks Like Unauthorized Access";
                    break;
                case 404:
                    model.addAttribute("PageTitle", "404 Error");
                    errorMsg = "Resource not found";
                    break;
                case 403:
                    model.addAttribute("PageTitle", "403 Error");
                    errorMsg = "Access Forbidden";
                    break;
                case 500:
                    model.addAttribute("PageTitle", "500 Error");
                    errorMsg = "Internal Server Error";
                    break;
                default:
                    model.addAttribute("PageTitle", errorCode+" Error");
                    errorMsg = "Something Went Wrong";
            }
        }
        // noticeTitle,noticeMessage,msgUrl,noticeBg
        model.addAttribute("noticeTitle",errorCode+" Error");
        model.addAttribute("noticeMessage",errorMsg);
        model.addAttribute("noticeBg","bg-danger");

        model.addAttribute("message", errorMsg);
        model.addAttribute("statusCode", errorCode);
        return "pages/error";
    }
}