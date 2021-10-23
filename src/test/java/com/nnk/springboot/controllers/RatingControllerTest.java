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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = RatingController.class)
@ContextConfiguration
@WithMockUser(roles = "USER")
public class RatingControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private RatingService ratingService;

	@MockBean
	private RatingRepository ratingRepository;

	@Test
	public void testGetRating() throws Exception {
		mockMvc.perform(get("/rating/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate")
				.param("account", "acc").param("type", "typ")
				.param("bidQuantity", "13.7"))
				.andExpect(redirectedUrl("/rating/list"));
	}

	@Test
	public void testUpdateForm() throws Exception {
		Optional<Rating> bid = Optional.ofNullable(new Rating("Moodys Rating",
				"Sand PRating", "Fitch Rating", 10));
		Mockito.when(ratingService.getRatingById(1)).thenReturn(bid);
		mockMvc.perform(get("/rating/update/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
				.param("moodysRating", "Moodys Rating")
				.param("sandPRating", "Sand PRating")
				.param("fitchRating", "Fitch Rating").param("orderNumber", "1"))
				.andExpect(redirectedUrl("/rating/list"));
	}

	@Test
	public void testDeleteBid() throws Exception {
		Optional<Rating> bid = Optional.ofNullable(new Rating("Moodys Rating",
				"Sand PRating", "Fitch Rating", 10));
		Mockito.when(ratingService.getRatingById(1)).thenReturn(bid);
		mockMvc.perform(get("/rating/delete/1"))
				.andExpect(redirectedUrl("/rating/list"));
	}

	@Test
	public void testUpdateBidError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
				.param("sandPRating", "Sand PRating")
				.param("fitchRating", "Fitch Rating").param("orderNumber", "a"))
				.andExpect(status().isOk());

	}

	@Test
	public void testAddBidEr() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate")
				.param("sandPRating", "Sand PRating")
				.param("fitchRating", "Fitch Rating").param("orderNumber", "a"))
				.andExpect(status().isOk());
	}
}
