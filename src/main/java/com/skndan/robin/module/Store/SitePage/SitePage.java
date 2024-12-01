package com.skndan.robin.module.Store.SitePage;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.module.Store.Store.Store;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SitePage extends BaseEntity{

  public String name;

  public String slug;

  public String description;
  
  public String metaTitle;

  public String metaDescription;

  public String metaKeywords;
 
  @ManyToOne
  public Store store;
}
