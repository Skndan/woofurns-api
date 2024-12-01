package com.skndan.robin.module.Product.Attribute;

import com.skndan.robin.entity.BaseEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Attribute extends BaseEntity {

    public String code;

    public String name;

}
