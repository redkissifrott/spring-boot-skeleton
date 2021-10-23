package com.nnk.springboot.domain;

import java.util.Objects;

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
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String moodysRating;

	private String sandPRating;

	private String fitchRating;

	private Integer orderNumber;

	public Rating(String moodysRating, String sandPRating, String fitchRating,
			Integer orderNumber) {
		super();
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		return Objects.equals(fitchRating, other.fitchRating)
				&& Objects.equals(id, other.id)
				&& Objects.equals(moodysRating, other.moodysRating)
				&& Objects.equals(orderNumber, other.orderNumber)
				&& Objects.equals(sandPRating, other.sandPRating);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fitchRating, id, moodysRating, orderNumber,
				sandPRating);
	}

}
