package com.skndan.robin.repo.product;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.product.AttributeValue;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface AttributeValueRepo
    extends CrudRepository<AttributeValue, UUID>, PagingAndSortingRepository<AttributeValue, UUID> {

  Page<AttributeValue> findAllByActive(boolean active, PageRequest of);

  Set<AttributeValue> findAllByAttributeIdAndActive(UUID attributeId, boolean active);
}