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
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = TradeController.class)
@ContextConfiguration
@WithMockUser(roles = "USER")
public class TradeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private TradeService tradeService;

	@MockBean
	private TradeRepository tradeRepository;

	@Test
	public void testGetTrade() throws Exception {
		mockMvc.perform(get("/trade/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
				.param("account", "acc").param("type", "typ")
				.param("buyQuantity", "13.7"))
				.andExpect(redirectedUrl("/trade/list"));
	}

	@Test
	public void testUpdateForm() throws Exception {
		Optional<Trade> bid = Optional
				.ofNullable(new Trade("account", "type", 13.7));
		Mockito.when(tradeService.getTradeById(1)).thenReturn(bid);
		mockMvc.perform(get("/trade/update/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
				.param("account", "acc").param("type", "typ")
				.param("buyQuantity", "13.7"))
				.andExpect(redirectedUrl("/trade/list"));
	}

	@Test
	public void testDeleteBid() throws Exception {
		Optional<Trade> bid = Optional
				.ofNullable(new Trade("account", "type", 13.7));
		Mockito.when(tradeService.getTradeById(1)).thenReturn(bid);
		mockMvc.perform(get("/trade/delete/1"))
				.andExpect(redirectedUrl("/trade/list"));
	}

	@Test
	public void testUpdateBidError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
				.param("type", "typ").param("buyQuantity", "a"))
				.andExpect(status().isOk());

	}

	@Test
	public void testAddBidEr() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
				.param("type", "typ").param("buyQuantity", "a"))
				.andExpect(status().isOk());
	}
}
