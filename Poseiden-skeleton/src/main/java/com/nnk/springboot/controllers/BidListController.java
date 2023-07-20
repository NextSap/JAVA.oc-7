package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BidListController {

    private final Logger logger = LogManager.getLogger(BidListController.class);
    private final BidListRepository bidListRepository;

    @Autowired
    public BidListController(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListRepository.findAll());
        logger.info("GET /bidList/list called sucessfully");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("GET /bidList/add called successfully");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("POST /bidList/validate called with errors: " + result.getAllErrors());
            return "bidList/add";
        }

        bidListRepository.save(bid);
        model.addAttribute("bidList", bidListRepository.findAll());
        logger.info("POST /bidList/validate called and saved successfully");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<BidList> bid = bidListRepository.findById(id);

        if (bid.isEmpty()) {
            logger.error("GET /bidList/update/" + id + " called but not found");
            return "404";
        }

        bid.get().setId(id);
        model.addAttribute("bid", bid.get());
        logger.info("GET /bidList/update/" + id + " called successfully");
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("POST /bidList/update/" + id + " called with errors: " + result.getAllErrors());
            return "bidList/update";
        }

        bidList.setId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidList", bidListRepository.findAll());
        logger.info("POST /bidList/update/" + id + " called and saved successfully");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListRepository.deleteById(id);
        model.addAttribute("bidList", bidListRepository.findAll());
        logger.info("GET /bidList/delete/" + id + " called successfully");
        return "redirect:/bidList/list";
    }
}
