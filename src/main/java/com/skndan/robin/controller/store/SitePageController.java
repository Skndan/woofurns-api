package com.skndan.robin.controller.store;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.skndan.robin.config.EntityCopyUtils;
import com.skndan.robin.entity.store.SitePage;
import com.skndan.robin.repo.store.SitePageRepo;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/site-page")
@Authenticated
@Tag(name = "Site Page", description = "Site Page")
public class SitePageController {

    @Inject
    SitePageRepo sitePageRepo;

    @Inject
    EntityCopyUtils entityCopyUtils;

    // TODO: add company
    @GET
    @Path("/get-by-store/{storeId}")
    public Response list(
            @PathParam("storeId") UUID storeId,
            @QueryParam("pageNo") @DefaultValue("0") int pageNo,
            @QueryParam("pageSize") @DefaultValue("25") int pageSize,
            @QueryParam("sortField") @DefaultValue("createdAt") String sortField,
            @QueryParam("sortDir") @DefaultValue("ASC") String sortDir) {

        Sort sortSt = sortDir.equals("DESC") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        Page<SitePage> brandList = sitePageRepo.findAllByStoreIdAndActive(storeId, true,
                PageRequest.of(pageNo, pageSize, sortSt));

        return Response.ok(brandList).status(200).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") UUID id) {
        Optional<SitePage> optional = sitePageRepo.findById(id);

        if (optional.isPresent()) {
            SitePage customScript = optional.get();
            return Response.ok(customScript).status(200).build();
        }

        throw new IllegalArgumentException("No department with id " + id + " exists");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(SitePage customScript) {
        if (customScript.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        sitePageRepo.save(customScript);
        return Response.ok(customScript).status(201).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, SitePage greeting) {
        Optional<SitePage> optional = sitePageRepo.findById(id);

        if (optional.isPresent()) {
            SitePage customScript = optional.get();
            entityCopyUtils.copyProperties(customScript, greeting);
            SitePage updateproductCategory = sitePageRepo.save(customScript);
            return Response.ok(updateproductCategory).status(200).build();
        }

        throw new IllegalArgumentException("No Department with id " + id + " exists");
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") UUID id) {

        SitePage entity = sitePageRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Department with id of " + id + " does not exist.", 404));

        // check if employees are tied up with department
        // Set<Profile> profile = profileRepo.findAllByDepartmentId(entity.getId());

        // if (profile.size() > 0) {
        // throw new WebApplicationException("There are " + profile.size() + " employees
        // in " + entity.getName() + ".",
        // 400);
        // }

        entity.setActive(false);
        sitePageRepo.save(entity);
        return Response.status(204).build();
    }
}
