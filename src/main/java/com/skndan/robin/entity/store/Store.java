package com.skndan.robin.entity.store;

import com.skndan.robin.entity.BaseEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Store extends BaseEntity{

  public String name;

  public String slug;

  public String metaTitle;

  public String metaDescription;

  public String metaKeywords;

  public String whatsAppEnabled;
  
  public String whatsAppNumber;

  public String whatsAppDefaultMessage;

  public String email;

  public String phone;

  public String addressLine1;

  public String addressLine2;

  public String city;

  public String state;

  public String pincode;

}
