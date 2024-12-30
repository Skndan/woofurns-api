package com.skndan.robin.module.Store.CustomScript;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.StatusEnum;
import com.skndan.robin.module.Store.Store.Store;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomScript extends BaseEntity{
 
  public String routeParam;

  public boolean enableHeaderScript;

  public String headerScript;

  public boolean enableFooterScript;

  public String footerScript;
  
  @Enumerated(EnumType.STRING)
  public StatusEnum status = StatusEnum.PUBLIC;

  @ManyToOne
  public Store store;
}
