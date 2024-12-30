package com.skndan.robin.module.Product.Collection;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CollectionRepo
    extends CrudRepository<Collection, UUID>, PagingAndSortingRepository<Collection, UUID> {

  Page<Collection> findAllByActive(boolean active, PageRequest of);

}