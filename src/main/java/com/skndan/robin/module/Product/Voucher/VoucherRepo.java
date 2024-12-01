package com.skndan.robin.module.Product.Voucher;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface VoucherRepo
    extends CrudRepository<Voucher, UUID>, PagingAndSortingRepository<Voucher, UUID> {

  Page<Voucher> findAllByActive(boolean active, PageRequest of);

}