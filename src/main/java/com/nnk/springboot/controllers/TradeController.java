package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@Controller
public class TradeController {

	@Autowired
	TradeService tradeService;

	private final Logger logger = LoggerFactory
			.getLogger(TradeController.class);

	@RequestMapping("/trade/list")
	public String home(Model model) {
		model.addAttribute("listTrades", tradeService.getTrades());
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addTradeForm(Trade trade) {
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result,
			Model model) {
		logger.info(
				"Request : /trade/add - Trade : Id = {} - Account = {} - Type = {} - Buy Quantity = {}",
				trade.getTradeId(), trade.getAccount(), trade.getType(),
				trade.getBuyQuantity());
		if (!result.hasErrors()) {
			tradeService.saveTrade(trade);
			model.addAttribute("listTrades", tradeService.getTrades());
			logger.info("Add Trade validated");
			return "redirect:/trade/list";
		}
		logger.info("Errors in the form");
		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeService.getTradeById(id).get();
		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id,
			@Valid Trade trade, BindingResult result, Model model) {
		logger.info(
				"Request : /trade/update - Trade : Id = {} - Account = {} - Type = {} - Buy Quantity = {}",
				trade.getTradeId(), trade.getAccount(), trade.getType(),
				trade.getBuyQuantity());
		if (!result.hasErrors()) {
			tradeService.saveTrade(trade);
			model.addAttribute("listTrades", tradeService.getTrades());
			logger.info("Update Trade validated");
			return "redirect:/trade/list";
		}
		logger.info("Errors in the form");
		model.addAttribute("trade", trade);
		return "/trade/update";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeService.getTradeById(id).get();
		logger.info("Request : /trade/delete/{}", trade.getTradeId());
		tradeService.deleteTrade(trade);
		logger.info("Return : Delete OK");
		return "redirect:/trade/list";
	}
}
