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
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = CurvePointController.class)
@ContextConfiguration
@WithMockUser(roles = "USER")
public class CurvePointControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private CurvePointService curvePointService;

	@MockBean
	private CurvePointRepository curvePointRepository;

	@Test
	public void testGetCurvePoint() throws Exception {
		mockMvc.perform(get("/curvePoint/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
				.param("curveId", "1").param("term", "13.7")
				.param("value", "18.3"))
				.andExpect(redirectedUrl("/curvePoint/list"));
	}

	@Test
	public void testUpdateForm() throws Exception {
		Optional<CurvePoint> bid = Optional
				.ofNullable(new CurvePoint(1, 17.3, 13.7));
		Mockito.when(curvePointService.getCurvePointById(1)).thenReturn(bid);
		mockMvc.perform(get("/curvePoint/update/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
				.param("curveId", "1").param("term", "13.7")
				.param("value", "18.3"))
				.andExpect(redirectedUrl("/curvePoint/list"));
	}

	@Test
	public void testDeleteBid() throws Exception {
		Optional<CurvePoint> bid = Optional
				.ofNullable(new CurvePoint(1, 17.3, 13.7));
		Mockito.when(curvePointService.getCurvePointById(1)).thenReturn(bid);
		mockMvc.perform(get("/curvePoint/delete/1"))
				.andExpect(redirectedUrl("/curvePoint/list"));
	}

	@Test
	public void testUpdateBidError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
				.param("curveId", "a").param("term", "13.7")
				.param("value", "18.3")).andExpect(status().isOk());

	}

	@Test
	public void testAddBidEr() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
				.param("curveId", "a").param("term", "13.7")
				.param("value", "18.3")).andExpect(status().isOk());
	}
}
