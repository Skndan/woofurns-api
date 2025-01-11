package com.skndan.robin.controller.product;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.skndan.robin.config.EntityCopyUtils;
import com.skndan.robin.entity.product.Attribute;
import com.skndan.robin.entity.product.AttributeValue;
import com.skndan.robin.repo.product.AttributeRepo;
import com.skndan.robin.repo.product.AttributeValueRepo;

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

@Path("/api/v1/attribute")
@Authenticated
@Tag(name = "Attribute", description = "Attribute")
public class AttributeController {

    @Inject
    AttributeRepo attributeRepo;

    @Inject
    AttributeValueRepo attributeValueRepo;

    @Inject
    EntityCopyUtils entityCopyUtils;

    // TODO: add company
    @GET
    public Response list(
            @QueryParam("pageNo") @DefaultValue("0") int pageNo,
            @QueryParam("pageSize") @DefaultValue("25") int pageSize,
            @QueryParam("sortField") @DefaultValue("createdAt") String sortField,
            @QueryParam("sortDir") @DefaultValue("ASC") String sortDir) {

        Sort sortSt = sortDir.equals("DESC") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        Page<Attribute> attributeList = attributeRepo.findAllByActive(true,
                PageRequest.of(pageNo, pageSize, sortSt));

        return Response.ok(attributeList).status(200).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") UUID id) {
        Optional<Attribute> optional = attributeRepo.findById(id);

        if (optional.isPresent()) {
            Attribute attribute = optional.get();
            return Response.ok(attribute).status(200).build();
        }

        throw new IllegalArgumentException("No department with id " + id + " exists");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(Attribute attribute) {
        if (attribute.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        attributeRepo.save(attribute);
        return Response.ok(attribute).status(201).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, Attribute greeting) {
        Optional<Attribute> optional = attributeRepo.findById(id);

        if (optional.isPresent()) {
            Attribute attribute = optional.get();
            entityCopyUtils.copyProperties(attribute, greeting);
            Attribute updateproductCategory = attributeRepo.save(attribute);
            return Response.ok(updateproductCategory).status(200).build();
        }

        throw new IllegalArgumentException("No Department with id " + id + " exists");
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") UUID id) {

        Attribute entity = attributeRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Department with id of " + id + " does not exist.", 404));

        // check if employees are tied up with department
        // Set<Profile> profile = profileRepo.findAllByDepartmentId(entity.getId());

        // if (profile.size() > 0) {
        // throw new WebApplicationException("There are " + profile.size() + " employees
        // in " + entity.getName() + ".",
        // 400);
        // }

        entity.setActive(false);
        attributeRepo.save(entity);
        return Response.status(204).build();
    }

    ///////////////////////////////////////////////////////////////////////

    // TODO: add company
    @GET
    @Path("/attribute-value/{attributeId}")
    public Response listValue(
            @PathParam("attributeId") UUID attributeId,
            @QueryParam("pageNo") @DefaultValue("0") int pageNo,
            @QueryParam("pageSize") @DefaultValue("25") int pageSize,
            @QueryParam("sortField") @DefaultValue("createdAt") String sortField,
            @QueryParam("sortDir") @DefaultValue("ASC") String sortDir) {

        Sort sortSt = sortDir.equals("DESC") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        Page<AttributeValue> attributeList = attributeValueRepo.findAllByAttributeIdAndActive(attributeId, true,
                PageRequest.of(pageNo, pageSize, sortSt));

        return Response.ok(attributeList).status(200).build();
    }

    @GET
    @Path("/value/{id}")
    public Response getValueByID(@PathParam("id") UUID id) {
        Optional<AttributeValue> optional = attributeValueRepo.findById(id);

        if (optional.isPresent()) {
            AttributeValue attribute = optional.get();
            return Response.ok(attribute).status(200).build();
        }

        throw new IllegalArgumentException("No department with id " + id + " exists");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/value")
    public Response addValue(AttributeValue attribute) {
        if (attribute.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        attributeValueRepo.save(attribute);
        return Response.ok(attribute).status(201).build();
    }

    @PUT
    @Path("/value/{id}")
    public Response updateValue(@PathParam("id") UUID id, AttributeValue greeting) {
        Optional<AttributeValue> optional = attributeValueRepo.findById(id);

        if (optional.isPresent()) {
            AttributeValue attribute = optional.get();
            entityCopyUtils.copyProperties(attribute, greeting);
            AttributeValue updateproductCategory = attributeValueRepo.save(attribute);
            return Response.ok(updateproductCategory).status(200).build();
        }

        throw new IllegalArgumentException("No Department with id " + id + " exists");
    }

    @DELETE
    @Path("/value/{id}")
    @Transactional
    public Response deleteValue(@PathParam("id") UUID id) {

        AttributeValue entity = attributeValueRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Department with id of " + id + " does not exist.", 404));

        // check if employees are tied up with department
        // Set<Profile> profile = profileRepo.findAllByDepartmentId(entity.getId());

        // if (profile.size() > 0) {
        // throw new WebApplicationException("There are " + profile.size() + " employees
        // in " + entity.getName() + ".",
        // 400);
        // }

        entity.setActive(false);
        attributeValueRepo.save(entity);
        return Response.status(204).build();
    }
}
