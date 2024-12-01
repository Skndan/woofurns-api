package com.skndan.robin.module.Product.ProductCategory;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProductCategoryRepo
    extends CrudRepository<ProductCategory, UUID>, PagingAndSortingRepository<ProductCategory, UUID> {

  Page<ProductCategory> findAllByActive(boolean active, PageRequest of);

}