package com.nnk.springboot.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = BidListController.class)
@ContextConfiguration
@WithMockUser(roles = "USER")
public class BidListControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BidListService bidListService;

	@MockBean
	private BidListRepository bidListRepository;

	// @Test
	// public void bidListAdd() throws Exception {
	// this.mockMvc.perform(post("/bidList/validate")
	// .contentType(MediaType.APPLICATION_JSON).content(
	// "{ \"account\":\"Account Test\", \"type\":\"Type Test\",
	// \"bidQuantity\":\"10\" }"))
	// .andExpect(status().isOk());
	// }

	@Test
	// @WithMockUser(username = "postmanTest", authorities = "USER")
	public void bidListAdd() throws Exception {
		mockMvc.perform(post("/bidList/validate")
				.contentType(MediaType.APPLICATION_JSON).content(
						"{ \"account\":\"Account Test\", \"type\":\"Type Test\", \"bidQuantity\":\"10\" }"))
				.andExpect(status().isOk());
	}
}
