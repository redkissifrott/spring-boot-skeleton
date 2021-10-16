package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.repositories.BidListRepository;

@ExtendWith(MockitoExtension.class)
class BidListServiceTest {

	@Mock
	private BidListRepository bidListRepository;

	private BidListService bidListService;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
