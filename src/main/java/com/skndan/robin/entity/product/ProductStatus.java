package com.skndan.robin.entity.product;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.SaleStatus;
import com.skndan.robin.entity.common.StatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductStatus extends BaseEntity {

  @OneToOne
  private Product product;

  // Status ----------------------------------------------------

  public boolean refundable = false;

  public boolean warranty = false;

  public boolean featured = false;

  public boolean trending = false;

  public boolean returnable = false;

  @Enumerated(EnumType.STRING)
  public StatusEnum status;

  @Enumerated(EnumType.STRING)
  public SaleStatus saleStatus;

}
