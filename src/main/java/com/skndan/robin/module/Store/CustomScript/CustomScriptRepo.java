package com.skndan.robin.module.Store.CustomScript;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CustomScriptRepo
    extends CrudRepository<CustomScript, UUID>, PagingAndSortingRepository<CustomScript, UUID> {

  Page<CustomScript> findAllByStoreIdAndActive(UUID storeId, boolean active, PageRequest of);

}