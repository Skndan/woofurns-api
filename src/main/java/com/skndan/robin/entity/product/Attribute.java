package com.skndan.robin.entity.product;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.AttributeStyle;
import com.skndan.robin.entity.common.DocStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Attribute extends BaseEntity {

    public String code;

    public String name;

    @Enumerated(EnumType.STRING)
    public AttributeStyle style;

    @Enumerated(EnumType.STRING)
    public DocStatus status = DocStatus.DRAFT;

}
