package com.skndan.robin.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.FileEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface FileRepo extends CrudRepository<FileEntity, UUID>, PagingAndSortingRepository<FileEntity, UUID> {
  
  void deleteByFileName(String name);

}