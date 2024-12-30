package com.skndan.robin.entity.store;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.SideEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HeaderLink extends BaseEntity {

  public String title;

  public String link;

  @Enumerated(EnumType.STRING)
  public SideEnum status = SideEnum.LEFT;

  @ManyToOne
  public Store store;
}
