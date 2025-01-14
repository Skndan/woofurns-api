package com.skndan.robin.entity.product;

import com.skndan.robin.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seo extends BaseEntity {

  @OneToOne
  private Product product;

  public String metaTitle;

  public String metaKeyword;

  public String metaDescription;

  // TODO: FileEntity image;
}
