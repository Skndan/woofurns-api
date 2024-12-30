package com.skndan.robin.module.Store.FooterLink;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
 

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface FooterLinkRepo
    extends CrudRepository<FooterLink, UUID>, PagingAndSortingRepository<FooterLink, UUID> {

  Page<FooterLink> findAllByStoreIdAndActive(UUID storeId, boolean active, PageRequest of);

}