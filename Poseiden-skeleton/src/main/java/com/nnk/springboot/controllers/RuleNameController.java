package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class RuleNameController {

    private final Logger logger = LogManager.getLogger(RuleNameController.class);
    private final RuleNameRepository ruleNameRepository;

    @Autowired
    public RuleNameController(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("GET /ruleName/list called successfully");
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        logger.info("GET /ruleName/add called successfully");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.error("POST /ruleName/validate called with errors: " + result.getAllErrors());
            return "ruleName/add";
        }

        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("POST /ruleName/validate called and saved successfully");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        if(ruleName.isEmpty()) {
            logger.error("GET /ruleName/update/" + id + " called but not found");
            return "redirect:/ruleName/list";
        }

        ruleName.get().setId(id);
        model.addAttribute("ruleName", ruleName.get());
        logger.info("GET /ruleName/update/" + id + " called successfully");
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.error("POST /ruleName/update/" + id + " called with errors: " + result.getAllErrors());
            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("POST /ruleName/update/" + id + " called and saved successfully");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameRepository.deleteById(id);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("GET /ruleName/delete/" + id + " called successfully");
        return "redirect:/ruleName/list";
    }
}
