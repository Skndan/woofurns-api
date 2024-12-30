package com.skndan.robin.module.Product.Product;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProductRepo
    extends CrudRepository<Product, UUID>, PagingAndSortingRepository<Product, UUID> {

  Page<Product> findAllByActive(boolean active, PageRequest of);

}