package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@Controller
public class BidListController {

	@Autowired
	BidListService bidListService;

	@Autowired
	BidListRepository bidListRepository;

	private final Logger logger = LoggerFactory
			.getLogger(BidListController.class);

	@RequestMapping("/bidList/list")
	public String home(Model model) {
		model.addAttribute("listBids", bidListService.getBidLists());
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result,
			Model model) {
		logger.info(
				"Request : /bidList/add - Bid : Id = {} - Account = {} - Type = {} - Bid quantity = {}",
				bid.getBidListId(), bid.getAccount(), bid.getType(),
				bid.getBidQuantity());
		if (!result.hasErrors()) {
			bidListService.saveBidList(bid);
			model.addAttribute("listBids", bidListService.getBidLists());
			logger.info("Add Bid validated");
			return "redirect:/bidList/list";
		}
		logger.info("Errors in the form");
		return "bidList/add";
	}

	// va
}
