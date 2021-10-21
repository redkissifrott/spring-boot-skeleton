package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	public Iterable<Trade> getTrades() {
		return tradeRepository.findAll();
	}

	public Optional<Trade> getTradeById(Integer id) {
		return tradeRepository.findById(id);
	}

	public Trade saveTrade(Trade trade) {
		return tradeRepository.save(trade);
	}

	public Boolean deleteTrade(Trade bid) {
		tradeRepository.delete(bid);
		return true;
	}

}
