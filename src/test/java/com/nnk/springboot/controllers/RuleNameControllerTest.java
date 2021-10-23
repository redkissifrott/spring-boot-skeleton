package com.nnk.springboot.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = RuleNameController.class)
@ContextConfiguration
@WithMockUser(roles = "USER")
public class RuleNameControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private RuleNameService ruleNameService;

	@MockBean
	private RuleNameRepository ruleNameRepository;

	@Test
	public void testGetRuleName() throws Exception {
		mockMvc.perform(get("/ruleName/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
				.param("name", "name").param("description", "description")
				.param("json", "json").param("template", "template")
				.param("sqlStr", "sqlStr").param("sqlPart", "sqlPart"))
				.andExpect(redirectedUrl("/ruleName/list"));
	}

	@Test
	public void testUpdateForm() throws Exception {
		Optional<RuleName> bid = Optional.ofNullable(new RuleName("Rule Name",
				"Description", "Json", "Template", "SQL", "SQL Part"));
		Mockito.when(ruleNameService.getRuleNameById(1)).thenReturn(bid);
		mockMvc.perform(get("/ruleName/update/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
				.param("name", "name").param("description", "description")
				.param("json", "json").param("template", "template")
				.param("sqlStr", "sqlStr").param("sqlPart", "sqlPart"))
				.andExpect(redirectedUrl("/ruleName/list"));
	}

	@Test
	public void testDeleteBid() throws Exception {
		Optional<RuleName> bid = Optional.ofNullable(new RuleName("Rule Name",
				"Description", "Json", "Template", "SQL", "SQL Part"));
		Mockito.when(ruleNameService.getRuleNameById(1)).thenReturn(bid);
		mockMvc.perform(get("/ruleName/delete/1"))
				.andExpect(redirectedUrl("/ruleName/list"));
	}

	@Test
	public void testUpdateBidError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
				.param("description", "description").param("json", "json")
				.param("template", "template").param("sqlStr", "sqlStr")
				.param("sqlPart", "sqlPart")).andExpect(status().isOk());
	}

	@Test
	public void testAddBidEr() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
				.param("description", "description").param("json", "json")
				.param("template", "template").param("sqlStr", "sqlStr")
				.param("sqlPart", "sqlPart")).andExpect(status().isOk());
	}
}
