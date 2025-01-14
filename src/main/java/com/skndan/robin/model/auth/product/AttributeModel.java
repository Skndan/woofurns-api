package com.skndan.robin.model.auth.product;

import java.util.Set;

import com.skndan.robin.entity.product.Attribute;
import com.skndan.robin.entity.product.AttributeValue;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AttributeModel {

  private Attribute attribute;

  private Set<AttributeValue> values;
}
