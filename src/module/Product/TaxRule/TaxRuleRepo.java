package com.skndan.robin.module.Product.TaxRule;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface TaxRuleRepo
    extends CrudRepository<TaxRule, UUID>, PagingAndSortingRepository<TaxRule, UUID> {

  Page<TaxRule> findAllByActive(boolean active, PageRequest of);

}