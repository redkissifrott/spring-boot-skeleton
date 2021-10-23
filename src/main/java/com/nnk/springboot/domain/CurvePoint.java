package com.nnk.springboot.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "must not be null")
	private Integer curveId;

	private Timestamp asOfDate;

	private Double term;

	private Double value;

	private Timestamp creationDate;

	public CurvePoint(Integer curveId, Double term, Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurvePoint other = (CurvePoint) obj;
		return Objects.equals(asOfDate, other.asOfDate)
				&& Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(curveId, other.curveId)
				&& Objects.equals(id, other.id)
				&& Objects.equals(term, other.term)
				&& Objects.equals(value, other.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(asOfDate, creationDate, curveId, id, term, value);
	}
}
