package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private final Logger logger = LogManager.getLogger(UserController.class);
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userRepository.findAll());
        logger.info("GET /user/list called successfully");
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
        logger.info("GET /user/add called successfully");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("POST /user/validate called with errors: " + result.getAllErrors());
            return "user/add";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        logger.info("POST /user/validate called and saved successfully");
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            logger.error("GET /user/update/" + id + " called but not found");
            return "redirect:/user/list";
        }

        user.get().setId(id);
        user.get().setPassword("");
        model.addAttribute("user", user.get());
        logger.info("GET /user/update/" + id + " called successfully");
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("POST /user/update/" + id + " called with errors: " + result.getAllErrors());
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        logger.info("POST /user/update/" + id + " called and saved successfully");
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userRepository.deleteById(id);
        model.addAttribute("users", userRepository.findAll());
        logger.info("GET /user/delete/" + id + " called successfully");
        return "redirect:/user/list";
    }
}