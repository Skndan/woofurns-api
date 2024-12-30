package com.skndan.robin.entity.product;

import com.skndan.robin.entity.BaseEntity;
import com.skndan.robin.entity.common.StatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Brand extends BaseEntity {
    
    public String code;
    
    public String name;
    
    public String slug;
      
    @Enumerated(EnumType.STRING)
    public StatusEnum status;
    
    public boolean featured = false;
}
