package com.skndan.robin.module.Store.FooterLink;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.LinkEnum;
import com.skndan.robin.entity.common.StatusEnum;
import com.skndan.robin.module.Store.SitePage.SitePage;
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
public class FooterLink extends BaseEntity {

  public String title;

  public String link;

  @ManyToOne
  public SitePage sitePage;

  @Enumerated(EnumType.STRING)
  public LinkEnum linkType;

  @Enumerated(EnumType.STRING)
  public StatusEnum status = StatusEnum.PUBLIC;

  @ManyToOne
  public Store store;
}
