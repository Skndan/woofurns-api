package com.skndan.robin.controller.product;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.skndan.robin.config.EntityCopyUtils;
import com.skndan.robin.entity.product.TaxRule;
import com.skndan.robin.repo.product.TaxRuleRepo;

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

@Path("/api/v1/tax-rule")
@Authenticated
@Tag(name = "Tax Rule", description = "Tax Rule")
public class TaxRuleController {

    @Inject
    TaxRuleRepo taxRuleRepo;

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

        Page<TaxRule> taxList = taxRuleRepo.findAllByActive(true,
                PageRequest.of(pageNo, pageSize, sortSt));

        return Response.ok(taxList).status(200).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") UUID id) {
        Optional<TaxRule> optional = taxRuleRepo.findById(id);

        if (optional.isPresent()) {
            TaxRule taxRule = optional.get();
            return Response.ok(taxRule).status(200).build();
        }

        throw new IllegalArgumentException("No department with id " + id + " exists");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(TaxRule taxRule) {
        if (taxRule.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        taxRuleRepo.save(taxRule);
        return Response.ok(taxRule).status(201).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, TaxRule greeting) {
        Optional<TaxRule> optional = taxRuleRepo.findById(id);

        if (optional.isPresent()) {
            TaxRule taxRule = optional.get();
            entityCopyUtils.copyProperties(taxRule, greeting);
            TaxRule updateproductCategory = taxRuleRepo.save(taxRule);
            return Response.ok(updateproductCategory).status(200).build();
        }

        throw new IllegalArgumentException("No Department with id " + id + " exists");
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") UUID id) {

        TaxRule entity = taxRuleRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Department with id of " + id + " does not exist.", 404));

        // check if employees are tied up with department
        // Set<Profile> profile = profileRepo.findAllByDepartmentId(entity.getId());

        // if (profile.size() > 0) {
        // throw new WebApplicationException("There are " + profile.size() + " employees
        // in " + entity.getName() + ".",
        // 400);
        // }

        entity.setActive(false);
        taxRuleRepo.save(entity);
        return Response.status(204).build();
    }
}
