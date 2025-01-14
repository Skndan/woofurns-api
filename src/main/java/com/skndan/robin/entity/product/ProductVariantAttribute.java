package com.skndan.robin.entity.product;

import com.skndan.robin.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductVariantAttribute extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "product_variant_id", nullable = false)
  private ProductVariant productVariant;

  @ManyToOne
  private ProductAttribute productAttribute;

  private String attribute;

  private String value;
}
