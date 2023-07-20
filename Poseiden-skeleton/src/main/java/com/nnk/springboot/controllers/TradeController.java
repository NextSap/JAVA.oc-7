package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeController {

    private final Logger logger = LogManager.getLogger(TradeController.class);
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeController(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("GET /trade/list called successfully");
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        logger.info("GET /trade/add called successfully");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        result.getAllErrors().forEach(System.out::println);
        if (result.hasErrors()) {
            logger.error("POST /trade/validate called with errors: " + result.getAllErrors());
            return "trade/add";
        }

        tradeRepository.save(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("POST /trade/validate called and saved successfully");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Trade> trade = tradeRepository.findById(id);

        if (trade.isEmpty()) {
            logger.error("GET /trade/update/" + id + " called but not found");
            return "404";
        }

        trade.get().setId(id);
        model.addAttribute("trade", trade.get());
        logger.info("GET /trade/update/" + id + " called successfully");
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("POST /trade/update/" + id + " called with errors: " + result.getAllErrors());
            return "trade/update";
        }

        trade.setId(id);
        tradeRepository.save(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("POST /trade/update/" + id + " called and saved successfully");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeRepository.deleteById(id);
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("GET /trade/delete/" + id + " called successfully");
        return "redirect:/trade/list";
    }
}
