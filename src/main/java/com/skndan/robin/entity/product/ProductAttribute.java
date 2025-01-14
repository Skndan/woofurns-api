package com.skndan.robin.entity.product;

import java.util.HashSet;
import java.util.Set;

import com.skndan.robin.entity.BaseEntity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductAttribute extends BaseEntity {

  // De-normalize - Attributes
  private String name; // e.g., "Weight", "Color"

  // De-normalize - AttributeValues
  @ElementCollection
  private Set<String> values = new HashSet<>(); // e.g., ["1kg", "2kg"], ["Red", "Blue"]

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
}
