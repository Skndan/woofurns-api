package com.skndan.robin.repo.product;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.product.Brand;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface BrandRepo
    extends CrudRepository<Brand, UUID>, PagingAndSortingRepository<Brand, UUID> {

  Page<Brand> findAllByActive(boolean active, PageRequest of);

}