package com.skndan.robin.config;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Priority(1) // Set priority to ensure this filter runs early
@ApplicationScoped
public class SecurityFilter implements ContainerRequestFilter {

  // List of routes to exclude from authentication
  private static final List<String> EXCLUDED_ROUTES = List.of(
      "/public", "/health", "/swagger-ui", "/api/v1/brand" // Add your excluded paths here
  );

  @Override
  public void filter(ContainerRequestContext requestContext) {
    String path = requestContext.getUriInfo().getPath();

    // Check if the path matches any of the excluded routes
    if (isExcluded(path)) {
      return; // Skip authentication for excluded routes
    }

    // Let Quarkus OIDC handle the authentication and token validation
    if (requestContext.getSecurityContext().getUserPrincipal() == null) {
      requestContext.abortWith(
          Response.status(Response.Status.UNAUTHORIZED)
              .entity("Unauthorized: Token is missing or invalid")
              .build());
    }
  }

  // Check if the route is in the exclusion list
  private boolean isExcluded(String path) {
    return EXCLUDED_ROUTES.stream().anyMatch(path::startsWith);
  }
}
