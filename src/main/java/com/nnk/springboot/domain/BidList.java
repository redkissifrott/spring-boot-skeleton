package com.nnk.springboot.domain;

import java.sql.Timestamp;

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

	public BidList(int id,
			@NotBlank(message = "Account is mandatory") String account,
			@NotBlank(message = "Type is mandatory") String type,
			@NotNull(message = "Bid Quantity is mandatory") @Digits(integer = 2, fraction = 1, message = "Bid Quantity must be in the numeric format 'xx,x'") Double bidQuantity) {
		super();
		this.bidListId = id;
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}
}
