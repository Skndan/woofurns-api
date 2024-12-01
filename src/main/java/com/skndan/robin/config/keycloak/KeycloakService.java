package com.skndan.robin.config.keycloak;

import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class KeycloakService {

    @Inject
    Keycloak keycloak;

    @ConfigProperty(name = "keycloak.clientId")
    String clientId;
    
    @ConfigProperty(name = "keycloak.realm")
    String realm;

    public void createUser(String username, String email, String firstName, String lastName, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);

        // Create the user
        var usersResource = keycloak.realm(realm).users();
        var response = usersResource.create(user);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create user: " + response.getStatusInfo());
        }

        // Retrieve user ID
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // Set password
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        usersResource.get(userId).resetPassword(credential);
    }

    public List<RoleRepresentation> getAllRoles() {
        // Retrieve the client resource using the client ID
        ClientRepresentation clientResource = keycloak.realm(realm).clients().findByClientId(clientId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found: " + "robin-ui"));

        // Fetch and return roles for the client
        return keycloak.realm(realm).clients().get(clientResource.getId()).roles().list();
    }

    public void updateUser(String userId, String email, String firstName, String lastName) {
        var usersResource = keycloak.realm(realm).users();
        UserResource userResource = usersResource.get(userId);

        // Fetch the existing user representation
        UserRepresentation user = userResource.toRepresentation();

        // Update the fields
        if (email != null)
            user.setEmail(email);
        if (firstName != null)
            user.setFirstName(firstName);
        if (lastName != null)
            user.setLastName(lastName);

        // Update the user
        userResource.update(user);
    }

    public void resetPassword(String userId, String newPassword) {
        var usersResource = keycloak.realm(realm).users();
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newPassword);
        credential.setTemporary(false);

        usersResource.get(userId).resetPassword(credential);
    }

    public void assignClientRoles(String userId, List<RoleRepresentation> roles) {
        var clientResource = keycloak.realm(realm).clients().findByClientId(clientId).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));

        var clientIdKey = clientResource.getId();
        keycloak.realm(realm).users()
                .get(userId)
                .roles()
                .clientLevel(clientIdKey)
                .add(roles);
    }

    public void unassignClientRoles(String userId, List<RoleRepresentation> roles) {
        var clientResource = keycloak.realm(realm).clients().findByClientId(clientId).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));

        var clientIdKey = clientResource.getId();
        keycloak.realm(realm).users()
                .get(userId)
                .roles()
                .clientLevel(clientIdKey)
                .remove(roles);
    }

    public UserRepresentation getUserById(String userId) {
        var usersResource = keycloak.realm(realm).users();
        return usersResource.get(userId).toRepresentation();
    }

    public List<UserRepresentation> getAllUsers() {
        return keycloak.realm(realm).users().list();
    }

    public List<RoleRepresentation> getAssignedClientRoles(String userId) {
        // Fetch the client using the clientId
        var clientResource = keycloak.realm(realm).clients().findByClientId(clientId).stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));
    
        // Fetch roles assigned to the user for the specified client
        return keycloak.realm(realm)
            .users()
            .get(userId)
            .roles()
            .clientLevel(clientResource.getId())
            .listEffective(); // Use listEffective to include inherited roles
    }
    

}