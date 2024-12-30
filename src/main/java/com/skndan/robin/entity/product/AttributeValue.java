package com.skndan.robin.entity.product;

import com.skndan.robin.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AttributeValue extends BaseEntity {

	@ManyToOne
	public Attribute attribute;

	public String code;

	public String name;

}
