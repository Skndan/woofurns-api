package com.skndan.robin.repo.store;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.store.CustomScript;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CustomScriptRepo
    extends CrudRepository<CustomScript, UUID>, PagingAndSortingRepository<CustomScript, UUID> {

  Page<CustomScript> findAllByStoreIdAndActive(UUID storeId, boolean active, PageRequest of);

}