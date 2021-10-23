package com.nnk.springboot.domain;

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
@Table(name = "rulename")
public class RuleName {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	private String name;

	private String description;

	private String json;

	private String template;

	private String sqlStr;

	private String sqlPart;

	public RuleName(String name, String description, String json,
			String template, String sqlStr, String sqlPart) {
		super();
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleName other = (RuleName) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(id, other.id)
				&& Objects.equals(json, other.json)
				&& Objects.equals(name, other.name)
				&& Objects.equals(sqlPart, other.sqlPart)
				&& Objects.equals(sqlStr, other.sqlStr)
				&& Objects.equals(template, other.template);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, json, name, sqlPart, sqlStr,
				template);
	}

}
