package com.skndan.robin.module.Store.HeaderLink;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
 

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface HeaderLinkRepo
    extends CrudRepository<HeaderLink, UUID>, PagingAndSortingRepository<HeaderLink, UUID> {

  Page<HeaderLink> findAllByStoreIdAndActive(UUID storeId, boolean active, PageRequest of);

}