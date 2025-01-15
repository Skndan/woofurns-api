package com.skndan.robin.repo.product;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.product.ProductStatus;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProductStatusRepo
    extends CrudRepository<ProductStatus, UUID>, PagingAndSortingRepository<ProductStatus, UUID> {

  Optional<ProductStatus> findByProductId(UUID status);

}