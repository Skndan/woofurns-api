package com.skndan.robin.repo.product;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.product.Seo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface SeoRepo
    extends CrudRepository<Seo, UUID>, PagingAndSortingRepository<Seo, UUID> {

  Optional<Seo> findByProductId(UUID status);

}