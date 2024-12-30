package com.skndan.robin.entity.store;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.StatusEnum; 

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SiteFeature extends BaseEntity {

  public String description;
 
  @Enumerated(EnumType.STRING)
  public StatusEnum status = StatusEnum.PUBLIC;

  @ManyToOne
  public Store store;
}
