package com.skndan.robin.service.product;

import java.util.Set;

import com.skndan.robin.entity.product.Attribute;
import com.skndan.robin.entity.product.AttributeValue;
import com.skndan.robin.model.auth.product.AttributeModel;
import com.skndan.robin.repo.product.AttributeValueRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AttributeService {

  @Inject
  AttributeValueRepo attributeValueRepo;

  public AttributeModel getAttribute(Attribute attribute) {

    Set<AttributeValue> attributeList = attributeValueRepo.findAllByAttributeIdAndActive(attribute.getId(),
        true);

    AttributeModel model = new AttributeModel();
    model.setAttribute(attribute);
    model.setValues(attributeList);

    return model;
  }
}
