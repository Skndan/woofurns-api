package com.skndan.robin.repo.store;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.store.SitePage;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface SitePageRepo
    extends CrudRepository<SitePage, UUID>, PagingAndSortingRepository<SitePage, UUID> {

  Page<SitePage> findAllByStoreIdAndActive(UUID storeId, boolean active, PageRequest of);

}