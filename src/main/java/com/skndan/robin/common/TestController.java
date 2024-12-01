package com.skndan.robin.common;

import io.vertx.mutiny.ext.web.FileUpload;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/test")
public class TestController {
  // @POST
  //   @Consumes(MediaType.APPLICATION_JSON)
  //   @Produces(MediaType.APPLICATION_JSON)
  //   @Transactional
  //   public Response add(@FileUpload("file") FileUpload fileUpload) {
  //       if (collection.id != null) {
  //           throw new WebApplicationException("Id was invalidly set on request.", 422);
  //       }

  //       collectionRepo.save(collection);
  //       return Response.ok(collection).status(201).build();
  //   }
}
