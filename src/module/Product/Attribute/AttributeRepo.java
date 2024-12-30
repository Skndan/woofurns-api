package com.skndan.robin.module.Product.Attribute;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface AttributeRepo
    extends CrudRepository<Attribute, UUID>, PagingAndSortingRepository<Attribute, UUID> {

  Page<Attribute> findAllByActive(boolean active, PageRequest of);

}