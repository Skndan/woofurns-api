package com.skndan.robin.repo.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skndan.robin.entity.auth.Profile;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProfileRepo extends CrudRepository<Profile, UUID>, PagingAndSortingRepository<Profile, UUID> {

  Optional<Profile> findByUserId(String id);

  Optional<Profile> findByEmail(String email);

  Page<Profile> findAllByRoleId(String roleId, PageRequest of);

}
