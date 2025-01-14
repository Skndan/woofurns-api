package com.skndan.robin.entity.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.ProductType;
import com.skndan.robin.entity.common.SaleStatus;
import com.skndan.robin.entity.common.StatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    public String code;

    public String title;

    public String overview;

    public String description;

    public String slug;

    public BigDecimal purchased = BigDecimal.ZERO;

    public BigDecimal selling = BigDecimal.ZERO;

    public BigDecimal offered = BigDecimal.ZERO;

    @ManyToOne
    public Brand brand;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sale_start_at")
    public Date saleStartAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sale_end_at")
    public Date saleEndAt;

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<ProductCategory> categories = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType type; // SIMPLE or VARIANT

 // Shipping rule

    // Bundle deal
    
    @ManyToMany
    @JoinTable(name = "product_collection", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private Set<Collection> collections = new HashSet<>();


    // Inventory ----------------------------------------------------

    // Attributes and Attribute Combinations (for VARIANT Products)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductAttribute> attributes = new HashSet<>();

    // Setup ----------------------------------------------------

    // Shipping & Tax ----------------------------------------------------

    public boolean freeShipping = false;

    @ManyToOne
    public TaxRule taxRule;

    public String estimatedDeliveryText;

    public String returnPolicyText;

}
