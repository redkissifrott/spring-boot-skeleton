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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController {

	@Autowired
	RuleNameService ruleNameService;

	private final Logger logger = LoggerFactory
			.getLogger(RuleNameController.class);

	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		model.addAttribute("listRuleNames", ruleNameService.getRuleNames());
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleNameForm(RuleName ruleName) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result,
			Model model) {
		logger.info(
				"Request : /ruleName/add - RuleName : Id = {} - Name = {} - Description = {} - json = {} - Template = {} - sql = {}- sqlPart = {}",
				ruleName.getId(), ruleName.getName(), ruleName.getDescription(),
				ruleName.getJson(), ruleName.getTemplate(),
				ruleName.getSqlStr(), ruleName.getSqlPart());
		if (!result.hasErrors()) {
			ruleNameService.saveRuleName(ruleName);
			model.addAttribute("listRuleNames", ruleNameService.getRuleNames());
			logger.info("Add RuleName validated");
			return "redirect:/ruleName/list";
		}
		logger.info("Errors in the form");
		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameService.getRuleNameById(id).get();
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id,
			@Valid RuleName ruleName, BindingResult result, Model model) {
		logger.info(
				"Request : /ruleName/update - RuleName : Id = {} - Name = {} - Description = {} - json = {} - Template = {} - sql = {}- sqlPart = {}",
				ruleName.getId(), ruleName.getName(), ruleName.getDescription(),
				ruleName.getJson(), ruleName.getTemplate(),
				ruleName.getSqlStr(), ruleName.getSqlPart());
		if (!result.hasErrors()) {
			ruleNameService.saveRuleName(ruleName);
			model.addAttribute("listRuleNames", ruleNameService.getRuleNames());
			logger.info("Update RuleName validated");
			return "redirect:/ruleName/list";
		}
		logger.info("Errors in the form");
		model.addAttribute("ruleName", ruleName);
		return "/ruleName/update";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameService.getRuleNameById(id).get();
		logger.info("Request : /ruleName/delete/{}", ruleName.getId());
		ruleNameService.deleteRuleName(ruleName);
		logger.info("Return : Delete OK");
		return "redirect:/ruleName/list";
	}
}
