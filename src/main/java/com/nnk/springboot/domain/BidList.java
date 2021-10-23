package com.nnk.springboot.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

	@Id
	@Column(name = "BidListId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bidListId;

	@NotBlank(message = "Account is mandatory")
	private String account;

	@NotBlank(message = "Type is mandatory")
	private String type;

	@NotNull(message = "Bid Quantity is mandatory")
	@Digits(integer = 2, fraction = 1, message = "Bid Quantity must be in the numeric format 'xx,x'")
	private Double bidQuantity;

	private Double askQuantity;

	private Double bid;

	private Double ask;

	private String benchmark;

	private Timestamp bidListDate;

	private String commentary;

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

	public BidList(@NotBlank(message = "Account is mandatory") String account,
			@NotBlank(message = "Type is mandatory") String type,
			@NotNull(message = "Bid Quantity is mandatory") @Digits(integer = 2, fraction = 1, message = "Bid Quantity must be in the numeric format 'xx,x'") Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidList other = (BidList) obj;
		return Objects.equals(account, other.account)
				&& Objects.equals(ask, other.ask)
				&& Objects.equals(askQuantity, other.askQuantity)
				&& Objects.equals(benchmark, other.benchmark)
				&& Objects.equals(bid, other.bid)
				&& Objects.equals(bidListDate, other.bidListDate)
				&& bidListId == other.bidListId
				&& Objects.equals(bidQuantity, other.bidQuantity)
				&& Objects.equals(book, other.book)
				&& Objects.equals(commentary, other.commentary)
				&& Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(creationName, other.creationName)
				&& Objects.equals(dealName, other.dealName)
				&& Objects.equals(dealType, other.dealType)
				&& Objects.equals(revisionDate, other.revisionDate)
				&& Objects.equals(revisionName, other.revisionName)
				&& Objects.equals(security, other.security)
				&& Objects.equals(side, other.side)
				&& Objects.equals(sourceListId, other.sourceListId)
				&& Objects.equals(status, other.status)
				&& Objects.equals(trader, other.trader)
				&& Objects.equals(type, other.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, ask, askQuantity, benchmark, bid,
				bidListDate, bidListId, bidQuantity, book, commentary,
				creationDate, creationName, dealName, dealType, revisionDate,
				revisionName, security, side, sourceListId, status, trader,
				type);
	}
}
