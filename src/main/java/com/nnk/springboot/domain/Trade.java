package com.nnk.springboot.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TradeId")
	private Integer tradeId;

	private String account;

	private String type;

	private Double buyQuantity;

	private Double sellQuantity;

	private Double buyPrice;

	private Double sellPrice;

	private String benchmark;

	private Timestamp tradeDate;

	private String security;

	private String status;

	private String trader;

	private String book;

	private String creationName;

	private Timestamp creationDate;

	private String revisionName;

	private Timestamp revisionDate;

	private String dealName;

	private String dealType;

	private String sourceListId;

	private String side;

	public Trade(String account, String type, Double buyQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.buyQuantity = buyQuantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		return Objects.equals(account, other.account)
				&& Objects.equals(benchmark, other.benchmark)
				&& Objects.equals(book, other.book)
				&& Objects.equals(buyPrice, other.buyPrice)
				&& Objects.equals(buyQuantity, other.buyQuantity)
				&& Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(creationName, other.creationName)
				&& Objects.equals(dealName, other.dealName)
				&& Objects.equals(dealType, other.dealType)
				&& Objects.equals(revisionDate, other.revisionDate)
				&& Objects.equals(revisionName, other.revisionName)
				&& Objects.equals(security, other.security)
				&& Objects.equals(sellPrice, other.sellPrice)
				&& Objects.equals(sellQuantity, other.sellQuantity)
				&& Objects.equals(side, other.side)
				&& Objects.equals(sourceListId, other.sourceListId)
				&& Objects.equals(status, other.status)
				&& Objects.equals(tradeDate, other.tradeDate)
				&& Objects.equals(tradeId, other.tradeId)
				&& Objects.equals(trader, other.trader)
				&& Objects.equals(type, other.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, benchmark, book, buyPrice, buyQuantity,
				creationDate, creationName, dealName, dealType, revisionDate,
				revisionName, security, sellPrice, sellQuantity, side,
				sourceListId, status, tradeDate, tradeId, trader, type);
	}

}
