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
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = UserController.class)
@ContextConfiguration
@WithMockUser(roles = "USER")
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void testGetUser() throws Exception {
		mockMvc.perform(get("/user/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
				.param("fullname", "fullname").param("username", "username")
				.param("password", "password").param("role", "USER"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateForm() throws Exception {
		Optional<User> user = Optional.ofNullable(
				new User("fullname", "username", "password", "USER"));
		Mockito.when(userRepository.findById(1)).thenReturn(user);
		mockMvc.perform(get("/user/update/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateBid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
				.param("fullname", "fullname").param("username", "username")
				.param("password", "password").param("role", "USER"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteBid() throws Exception {
		Optional<User> user = Optional.ofNullable(
				new User("fullname", "username", "password", "USER"));
		Mockito.when(userRepository.findById(1)).thenReturn(user);
		mockMvc.perform(get("/user/delete/1"))
				.andExpect(redirectedUrl("/user/list"));
	}

	@Test
	public void testUpdateBidError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
				.param("username", "username").param("password", "password")
				.param("role", "USER")).andExpect(status().isOk());

	}

	@Test
	public void testAddBidEr() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
				.param("username", "username").param("password", "password")
				.param("role", "USER")).andExpect(status().isOk());
	}
}
