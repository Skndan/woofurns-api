package com.skndan.robin.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/api/static")
public class StaticController {

    @ConfigProperty(name = "file.static-location")
    String basePath;

    @GET
    @Path("/{path: .*}") // Matches all paths
    @Produces({"image/png", "image/jpeg"}) // Add more MIME types as needed
    public Response serveFile(@PathParam("path") String filePath) {
        File file = new File(basePath, filePath);
        if (!file.exists()) {
            // Return 404 if the file does not exist
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            // Return the file with an OK status
            return Response.ok(Files.readAllBytes(file.toPath()))
                    .type(Files.probeContentType(Paths.get(file.getAbsolutePath())))
                    .build();
        } catch (Exception e) {
            // Handle exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}