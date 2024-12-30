package com.skndan.robin.entity.store;

import com.skndan.robin.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SiteSetting extends BaseEntity{
 
  public String name;

  public String url;

  public String copyrightText;

  public String primaryColor;

  public String primaryHoverColor;
  
  public String metaTitle;

  public String metaDescription;

  public String metaKeywords;
 
  @ManyToOne
  public Store store;
}
