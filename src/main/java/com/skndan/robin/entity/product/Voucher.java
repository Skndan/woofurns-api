package com.skndan.robin.entity.product;

import java.math.BigDecimal;
import java.util.Date;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.PriceTypeEnum;
import com.skndan.robin.entity.common.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Voucher extends BaseEntity {

    public String code;

    public String title;

    public BigDecimal value = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    public PriceTypeEnum priceTypeEnum;

    public BigDecimal cappedPrice = BigDecimal.ZERO; // max offer

    public BigDecimal minSpent = BigDecimal.ZERO; // minspent per order

    public BigDecimal usageLimit = BigDecimal.ZERO; // how many vouchers

    public BigDecimal limitPerCustomer = BigDecimal.ZERO; // limit ver customer

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "starttime")
    public Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endtime")
    public Date endTime;

    @Enumerated(EnumType.STRING)
    public StatusEnum status;
}
