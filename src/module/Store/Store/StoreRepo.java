package com.skndan.robin.module.Store.Store;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
  
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface StoreRepo
    extends CrudRepository<Store, UUID>, PagingAndSortingRepository<Store, UUID> {

  Page<Store> findAllByActive(boolean active, PageRequest of);

}