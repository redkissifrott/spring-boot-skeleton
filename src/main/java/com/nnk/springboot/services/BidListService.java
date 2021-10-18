package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	private BidListRepository bidListRepository;

	public Iterable<BidList> getBidLists() {
		return bidListRepository.findAll();
	}

	public BidList saveBidList(BidList bidList) {
		return bidListRepository.save(bidList);
	}

	public Boolean deleteBidList(BidList bid) {
		bidListRepository.delete(bid);
		return true;
	}

}
