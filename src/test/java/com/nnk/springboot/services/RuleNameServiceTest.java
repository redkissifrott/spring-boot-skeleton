package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.RuleName;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameServiceTest {

	@Autowired
	private RuleNameService ruleNameService;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json",
				"Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameService.saveRuleName(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameService.saveRuleName(rule);
		Assert.assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = (List<RuleName>) ruleNameService
				.getRuleNames();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameService.deleteRuleName(rule);
		Optional<RuleName> ruleList = ruleNameService.getRuleNameById(id);
		Assert.assertFalse(ruleList.isPresent());
	}
}
