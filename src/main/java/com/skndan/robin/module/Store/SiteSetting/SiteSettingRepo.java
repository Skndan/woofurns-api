package com.skndan.robin.module.Store.SiteSetting;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface SiteSettingRepo
    extends CrudRepository<SiteSetting, UUID>, PagingAndSortingRepository<SiteSetting, UUID> {

  Page<SiteSetting> findAllByStoreIdAndActive(UUID storeId, boolean active, PageRequest of);

}