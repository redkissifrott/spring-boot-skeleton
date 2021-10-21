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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@Controller
public class CurvePointController {

	@Autowired
	CurvePointService curvePointService;

	private final Logger logger = LoggerFactory
			.getLogger(CurvePointController.class);

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		model.addAttribute("listCurvePoints",
				curvePointService.getCurvePoints());
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		logger.info(
				"Request : /curvePoint/add - Curve : CurveId = {} - Term = {} - Value = {}",
				curvePoint.getCurveId(), curvePoint.getTerm(),
				curvePoint.getValue());
		if (!result.hasErrors()) {
			curvePointService.saveCurvePoint(curvePoint);
			model.addAttribute("listCurvePoints",
					curvePointService.getCurvePoints());
			logger.info("Add CurvePoint validated");
			return "redirect:/curvePoint/list";
		}
		logger.info("Errors in the form");
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curve = curvePointService.getCurvePointById(id).get();
		model.addAttribute("curvePoint", curve);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateCurvePoint(@PathVariable("id") Integer id,
			@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		logger.info(
				"Request : /curvePoint/update - Curve : Id = {} - Term = {} - Value = {}",
				curvePoint.getCurveId(), curvePoint.getTerm(),
				curvePoint.getValue());
		if (!result.hasErrors()) {
			curvePointService.saveCurvePoint(curvePoint);
			model.addAttribute("listCurvePoints",
					curvePointService.getCurvePoints());
			logger.info("Update CurvePoint validated");
			return "redirect:/curvePoint/list";
		}
		logger.info("Errors in the form");
		model.addAttribute("curvePoint", curvePoint);
		return "/curvePoint/update";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurvePoint(@PathVariable("id") Integer id,
			Model model) {
		CurvePoint curve = curvePointService.getCurvePointById(id).get();
		logger.info("Request : /curvePoint/delete/{}", curve.getId());
		curvePointService.deleteCurvePoint(curve);
		logger.info("Return : Delete OK");
		return "redirect:/curvePoint/list";
	}
}
