package com.skndan.robin.service.auth;

import com.skndan.robin.entity.auth.Profile;
import com.skndan.robin.exception.GenericException;
import com.skndan.robin.model.auth.AuthResponse;
import com.skndan.robin.repo.auth.ProfileRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProfileService {

  @Inject
  ProfileRepo profileRepo;

  public AuthResponse getProfile(String sub) {
    AuthResponse response = new AuthResponse();

    Profile profile = profileRepo.findByUserId(sub).orElseThrow(() -> new GenericException(400, "User not found"));

    if (!profile.getActive()) {
      throw new GenericException(400, "Your account is inactive. Please contact the administrator");
    }

    response.setProfileId(profile.getId().toString());
    response.setName((profile.getFirstName() + " " + profile.getLastName()).trim());
    response.setEmail(profile.getEmail());
    response.setMobile(profile.getMobile());

    return response;
  }
}
