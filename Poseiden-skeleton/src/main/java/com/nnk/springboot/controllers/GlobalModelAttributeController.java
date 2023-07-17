package com.nnk.springboot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributeController {

    @ModelAttribute("username")
    public String getUsername(HttpServletRequest request) {
        return request.getRemoteUser();
    }
}
