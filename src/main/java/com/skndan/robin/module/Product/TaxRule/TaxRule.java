package com.skndan.robin.module.Product.TaxRule;

import java.math.BigDecimal;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.PriceTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TaxRule extends BaseEntity {

    public String code;

    public String title;

    public BigDecimal price = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    public PriceTypeEnum priceTypeEnum;
}
