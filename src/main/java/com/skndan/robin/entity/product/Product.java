package com.skndan.robin.entity.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.StatusEnum;
import com.skndan.robin.entity.product.ProductCategory;
import com.skndan.robin.entity.product.TaxRule;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    public String code;

    public String title;

    public String slug;

    public BigDecimal purchased = BigDecimal.ZERO;

    public BigDecimal selling = BigDecimal.ZERO;

    public BigDecimal offered = BigDecimal.ZERO;

    @ManyToOne
    public ProductCategory category;

    public String overview;

    public String description;

    @Enumerated(EnumType.STRING)
    public StatusEnum status;

    @ManyToOne
    public Brand brand;

    @ManyToOne
    public TaxRule taxRule;

    public List<String> tags = new ArrayList<String>();

    // Shipping rule

    // Bundle deal
    
    @ManyToMany
    @JoinTable(name = "product_collection", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private Set<Collection> collections = new HashSet<>();

    public boolean refundable = false;

    public boolean warranty = false;

    public String metaTitle;

    public String metaKeyword;

    public String metaDescription;
}
