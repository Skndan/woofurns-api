package com.skndan.robin.module.Product.Collection;

import java.util.HashSet;
import java.util.Set;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.StatusEnum;
import com.skndan.robin.module.Product.Product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Collection extends BaseEntity {

    public String code;

    public String name;

    public String slug;

    @Enumerated(EnumType.STRING)
    public StatusEnum status;

    @ManyToMany(mappedBy = "collections")
    private Set<Product> products = new HashSet<>();

    public boolean featured = false;
}
