package com.skndan.robin.module.Auth;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.keycloak.common.Profile;

import com.skndan.robin.config.keycloak.KeycloakService;
import com.skndan.robin.exception.GenericException;
import com.skndan.robin.module.Auth.model.SignUpRequest;
import com.skndan.robin.module.Auth.model.UserRecord;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
@Authenticated
@Tag(name = "Authenticate", description = "Authenticate")
public class AuthController {

  @Inject
  private KeycloakService keycloakService;

  @Inject
  SecurityIdentity securityIdentity;

  @Inject
  private AuthService authService;

  @POST
  @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID")
  @APIResponses({
      @APIResponse(responseCode = "204", description = "Reservation confirmed"),
      @APIResponse(responseCode = "422", description = "Reservation can not be confirmed", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GenericException.class)))
  })
  public Response createUser(@RequestBody UserRecord userRegistrationRecord) {
    return Response.ok(keycloakService.createUser(userRegistrationRecord)).build();
  }

  @POST
  @Path("/sign-up")
  @Transactional
  public Response add(SignUpRequest dept) {
    Profile profile = authService.createProfile(dept);
    return Response.ok(profile).status(201).build();
  }

  @POST
  @Path("/callback")
  public Response handleGoogleCallback(@RequestBody AuthRequest authRequest) {
    return keycloakService.getSocialToken(authRequest);
  }
}
