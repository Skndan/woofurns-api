package com.skndan.robin.module.Product.ProductCategory;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skndan.robin.config.EntityCopyUtils;
import com.skndan.robin.entity.FileEntity;
import com.skndan.robin.service.EntityService;
import com.skndan.robin.service.FileService;

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

@Path("/api/product-category")
@Authenticated
@Tag(name = "Product Category", description = "Product Category")
public class ProductCategoryController {

    @Inject
    ProductCategoryRepo productCategoryRepo;

    @Inject
    EntityCopyUtils entityCopyUtils;

    @Inject
    FileService fileService;

    @Inject
    EntityService entityService;

    ObjectMapper mapper = new ObjectMapper();

    @Inject
    @ConfigProperty(name = "productCategory.path")
    String filePath;

    // TODO: add company
    @GET
    public Response list(
            @QueryParam("pageNo") @DefaultValue("0") int pageNo,
            @QueryParam("pageSize") @DefaultValue("25") int pageSize,
            @QueryParam("sortField") @DefaultValue("createdAt") String sortField,
            @QueryParam("sortDir") @DefaultValue("ASC") String sortDir) {

        Sort sortSt = sortDir.equals("DESC") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        Page<ProductCategory> categoryList = productCategoryRepo.findAllByActive(true,
                PageRequest.of(pageNo, pageSize, sortSt));

        return Response.ok(categoryList).status(200).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") UUID id) {
        Optional<ProductCategory> optional = productCategoryRepo.findById(id);

        if (optional.isPresent()) {
            ProductCategory productCategory = optional.get();
            return Response.ok(productCategory).status(200).build();
        }

        throw new IllegalArgumentException("No department with id " + id + " exists");
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(@MultipartForm MultipartFormDataInput input) throws IOException {

        try {
            ProductCategory productCategory = entityService.processMultipartRequest(input, ProductCategory.class);

            productCategory = productCategoryRepo.save(productCategory);

            FileEntity fileInfo = entityService.extractFile(input, filePath);

            productCategory.setImage(fileInfo);

            productCategory = productCategoryRepo.save(productCategory);

            return Response.ok(productCategory).status(201).build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to process your request");
        }

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, @MultipartForm MultipartFormDataInput input) {
        Optional<ProductCategory> optional = productCategoryRepo.findById(id);

        try {

            if (optional.isPresent()) {
                ProductCategory productCategory = optional.get();
                ProductCategory updatedProductCategory = entityService.processMultipartRequest(input,
                        ProductCategory.class);
                entityCopyUtils.copyProperties(productCategory, updatedProductCategory);

                FileEntity oldFileInfo = productCategory.getImage();
                fileService.deleteFileEntity(oldFileInfo, filePath);

                FileEntity fileInfo = entityService.extractFile(input, filePath);
                productCategory.setImage(fileInfo);

                productCategory = productCategoryRepo.save(productCategory);

                return Response.ok(productCategory).status(200).build();
            }

            throw new IllegalArgumentException("No Product Category with id " + id + " exists");

        } catch (Exception e) {
            throw new IllegalArgumentException("No Product Category with id " + id + " exists");
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") UUID id) {

        ProductCategory entity = productCategoryRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Department with id of " + id + " does not exist.", 404));
  
        entity.setActive(false);
        productCategoryRepo.save(entity);
        return Response.status(204).build();
    }
}
