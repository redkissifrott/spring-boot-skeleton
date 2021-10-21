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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {

	@Autowired
	RatingService ratingService;

	private final Logger logger = LoggerFactory
			.getLogger(RatingController.class);

	@RequestMapping("/rating/list")
	public String home(Model model) {
		model.addAttribute("listRatings", ratingService.getRatings());
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result,
			Model model) {
		logger.info(
				"Request : /rating/add - Rating : Id = {} - MoodysRating = {} - SandPRating = {} - FitchRating = {} - OrderNumber = {}",
				rating.getId(), rating.getMoodysRating(),
				rating.getSandPRating(), rating.getFitchRating(),
				rating.getOrderNumber());
		if (!result.hasErrors()) {
			ratingService.saveRating(rating);
			model.addAttribute("listRatings", ratingService.getRatings());
			logger.info("Add Rating validated");
			return "redirect:/rating/list";
		}
		logger.info("Errors in the form");
		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.getRatingById(id).get();
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id,
			@Valid Rating rating, BindingResult result, Model model) {
		logger.info(
				"Request : /rating/update - Rating : Id = {} - MoodysRating = {} - SandPRating = {} - FitchRating = {} - OrderNumber = {}",
				rating.getId(), rating.getMoodysRating(),
				rating.getSandPRating(), rating.getFitchRating(),
				rating.getOrderNumber());
		if (!result.hasErrors()) {
			ratingService.saveRating(rating);
			model.addAttribute("listRatings", ratingService.getRatings());
			logger.info("Update Rating validated");
			return "redirect:/rating/list";
		}
		logger.info("Errors in the form");
		model.addAttribute("rating", rating);
		return "/rating/update";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.getRatingById(id).get();
		logger.info("Request : /rating/delete/{}", rating.getId());
		ratingService.deleteRating(rating);
		logger.info("Return : Delete OK");
		return "redirect:/rating/list";
	}
}
