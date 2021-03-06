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
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = BidListController.class)
@ContextConfiguration
@WithMockUser(roles = "USER")
public class BidListControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private BidListService bidListService;

	@MockBean
	private BidListRepository bidListRepository;

	@Test
	public void testGetBidList() throws Exception {
		mockMvc.perform(get("/bidList/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
				.param("account", "acc").param("type", "typ")
				.param("bidQuantity", "13.7"))
				.andExpect(redirectedUrl("/bidList/list"));
	}

	@Test
	public void testUpdateForm() throws Exception {
		Optional<BidList> bid = Optional
				.ofNullable(new BidList("account", "type", 13.7));
		Mockito.when(bidListService.getBidListById(1)).thenReturn(bid);
		mockMvc.perform(get("/bidList/update/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
				.param("account", "acc").param("type", "typ")
				.param("bidQuantity", "13.7"))
				.andExpect(redirectedUrl("/bidList/list"));
	}

	@Test
	public void testDeleteBid() throws Exception {
		Optional<BidList> bid = Optional
				.ofNullable(new BidList("account", "type", 13.7));
		Mockito.when(bidListService.getBidListById(1)).thenReturn(bid);
		mockMvc.perform(get("/bidList/delete/1"))
				.andExpect(redirectedUrl("/bidList/list"));
	}

	@Test
	public void testUpdateBidError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
				.param("type", "typ").param("bidQuantity", "13.7"))
				.andExpect(status().isOk());

	}

	@Test
	public void testAddBidEr() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
				.param("type", "typ").param("bidQuantity", "13.7"))
				.andExpect(status().isOk());
	}
}
