package com.skndan.robin.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FileEntity extends BaseEntity { 
         
    public String fileName;

    public String hash;
    
    public String fileUrl;
}
