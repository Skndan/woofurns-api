package com.skndan.robin.module.Product.ProductCategory;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.FileEntity;
import com.skndan.robin.entity.common.StatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductCategory extends BaseEntity {
    
    public String code;
    
    public String name;
    
    public String slug;
    
    public String metaTitle;
    
    public String metaKeyword;
    
    public String metaDescription;
    
    public boolean showInFooter = false;

    @Enumerated(EnumType.STRING)
    public StatusEnum status;
    
    public boolean featured = false;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ProductCategory parent;

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductCategory> children = new HashSet<>();

    @OneToOne
    private FileEntity image;
}
