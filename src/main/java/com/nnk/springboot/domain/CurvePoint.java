package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

	public CurvePoint(int i, double d, double e) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer curveId;

	private Timestamp asOfDate;

	private Double term;

	private Double value;

	private Timestamp creationDate;
}
