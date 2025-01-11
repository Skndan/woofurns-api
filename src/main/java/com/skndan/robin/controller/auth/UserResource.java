package com.skndan.robin.controller.auth;
 

import com.skndan.robin.model.auth.AuthResponse;
import com.skndan.robin.service.auth.ProfileService;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/me")
@Authenticated
public class UserResource {

  @Inject
  SecurityIdentity securityIdentity;

  @Inject
  ProfileService profileService;

  @GET
  public Response me() {

    // DefaultJWTCallerPrincipal jwtPrincipal = (DefaultJWTCallerPrincipal) securityIdentity.getPrincipal();
    // String subject = jwtPrincipal.getSubject(); // Access the subject
    // AuthResponse response = profileService.getProfile(subject);
    AuthResponse response = new AuthResponse();
    response.setEmail("emailtorabbitt@gmail.com");
    response.setMobile("+918248547389");
    response.setName("Rk Rabbitt");
    response.setProfileId("0fb24a2d-b4b7-42f9-a705-2c4743630986");

    return Response.ok(response).build();
  }

  public static class User {

    private final String userName;

    User(SecurityIdentity identity) {
      this.userName = identity.getPrincipal().getName();
    }

    public String getUserName() {
      return userName;
    }
  }
}
