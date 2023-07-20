package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;

@Controller
public class CurveController {

    private final Logger logger = LogManager.getLogger(CurveController.class);

    private final CurvePointRepository curvePointRepository;

    @Autowired
    public CurveController(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("GET /curvePoint/list called successfully");
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        logger.info("GET /curvePoint/add called successfully");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("POST /curvePoint/validate called with errors: " + result.getAllErrors());
            return "curvePoint/add";
        }

        curvePointRepository.save(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("POST /curvePoint/validate called and saved successfully");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if (curvePoint.isEmpty()) {
            logger.error("GET /curvePoint/update/" + id + " called but not found");
            return "404";
        }

        curvePoint.get().setId(id);
        model.addAttribute("curvePoint", curvePoint.get());
        logger.info("GET /curvePoint/update/" + id + " called successfully");
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("POST /curvePoint/update/" + id + " called with errors: " + result.getAllErrors());
            return "curvePoint/update";
        }

        curvePoint.setCurveId(id);
        curvePointRepository.save(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("POST /curvePoint/update/" + id + " called and saved successfully");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointRepository.deleteById(id);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("GET /curvePoint/delete/" + id + " called successfully");
        return "curvePoint/list";
    }
}
