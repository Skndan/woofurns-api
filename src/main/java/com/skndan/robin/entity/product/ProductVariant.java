package com.skndan.robin.entity.product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.StatusEnum;
import com.skndan.robin.entity.common.StockStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductVariant extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private String name; // e.g., "Red 1kg"

  @Column(nullable = false)
  public BigDecimal purchased = BigDecimal.ZERO;

  @Column(nullable = false)
  public BigDecimal selling = BigDecimal.ZERO;

  @Column(nullable = false)
  public BigDecimal offered = BigDecimal.ZERO;

  @Column(nullable = false, unique = true)
  private String sku;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StockStatus stockStatus;

  // @OneToOne(cascade = CascadeType.ALL)
  // private FileEntity image;

  @Enumerated(EnumType.STRING)
  private StatusEnum status; // e.g., PRIVATE, PUBLIC
 
  @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ProductVariantAttribute> productVariantAttribute = new HashSet<>();

}
