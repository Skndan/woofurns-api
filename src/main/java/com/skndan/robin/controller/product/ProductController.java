package com.skndan.robin.controller.product;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.skndan.robin.config.EntityCopyUtils;
import com.skndan.robin.entity.common.DocStatus;
import com.skndan.robin.entity.product.Product;
import com.skndan.robin.entity.product.ProductStatus;
import com.skndan.robin.entity.product.Seo;
import com.skndan.robin.exception.GenericException;
import com.skndan.robin.repo.product.ProductRepo;
import com.skndan.robin.repo.product.ProductStatusRepo;
import com.skndan.robin.repo.product.SeoRepo;

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

@Path("/api/v1/product")
@Authenticated
@Tag(name = "Product", description = "Product")
public class ProductController {

    @Inject
    ProductRepo productRepo;

    @Inject
    ProductStatusRepo productStatusRepo;

    @Inject
    SeoRepo seoRepo;

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

        Page<Product> categoryList = productRepo.findAllByActive(true,
                PageRequest.of(pageNo, pageSize, sortSt));

        return Response.ok(categoryList).status(200).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") UUID id) {
        Optional<Product> optional = productRepo.findById(id);

        if (optional.isPresent()) {
            Product product = optional.get();
            return Response.ok(product).status(200).build();
        }

        throw new IllegalArgumentException("No department with id " + id + " exists");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(Product product) {
        if (product.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        productRepo.save(product);
        return Response.ok(product).status(201).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, Product greeting) {
        Optional<Product> optional = productRepo.findById(id);

        if (optional.isPresent()) {
            Product product = optional.get();
            entityCopyUtils.copyProperties(product, greeting);
            Product updateproductCategory = productRepo.save(product);
            return Response.ok(updateproductCategory).status(200).build();
        }

        throw new IllegalArgumentException("No Department with id " + id + " exists");
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") UUID id) {

        Product entity = productRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Department with id of " + id + " does not exist.", 404));

        // check if employees are tied up with department
        // Set<Profile> profile = profileRepo.findAllByDepartmentId(entity.getId());

        // if (profile.size() > 0) {
        // throw new WebApplicationException("There are " + profile.size() + " employees
        // in " + entity.getName() + ".",
        // 400);
        // }

        entity.setActive(false);
        productRepo.save(entity);
        return Response.status(204).build();
    }

    @GET
    @Path("/create")
    public Response createProduct(@PathParam("id") UUID id) {

        Optional<Product> optional = productRepo.findByStatus(DocStatus.DRAFT);

        Product product = new Product();

        if (optional.isPresent()) {
            product = optional.get();
            return Response.ok(product).status(200).build();
        } else {
            product = productRepo.save(product);
            return Response.ok(product).status(200).build();
        }
    }

    // product status

    @POST
    @Path("/status/{productId}")
    public Response createProductStatus(@PathParam("productId") UUID productId) {

        Optional<ProductStatus> optional = productStatusRepo.findByProductId(productId);

        ProductStatus productStatus = new ProductStatus();

        if (optional.isPresent()) {
            productStatus = optional.get();
            return Response.ok(productStatus).status(200).build();
        } else {
            Product product = productRepo.findById(productId).orElseThrow(() -> new GenericException(400, "No Product"));
            productStatus.setProduct(product);
            productStatus = productStatusRepo.save(productStatus);
            return Response.ok(productStatus).status(200).build();
        }
    }

    @PUT
    @Path("/status/{id}")
    public Response updateProductStatus(@PathParam("id") UUID id, ProductStatus productStatus) {
        Optional<ProductStatus> optional = productStatusRepo.findById(id);

        if (optional.isPresent()) {
            ProductStatus existingProductStatus = optional.get();
            entityCopyUtils.copyProperties(existingProductStatus, productStatus);
            ProductStatus updateProductStatus = productStatusRepo.save(existingProductStatus);
            return Response.ok(updateProductStatus).status(200).build();
        }

        throw new GenericException(400, "No Department with id " + id + " exists");
    }

    // seo
    @POST
    @Path("/seo/{id}")
    public Response createSeo(@PathParam("id") UUID id) {

        Optional<Seo> optional = seoRepo.findByProductId(id);

        Seo seo = new Seo();

        if (optional.isPresent()) {
            seo = optional.get();
            return Response.ok(seo).status(200).build();
        } else {
            Product product = productRepo.findById(id).orElseThrow(() -> new GenericException(400, "No Product"));
            seo.setProduct(product);
            seo = seoRepo.save(seo);
            return Response.ok(seo).status(200).build();
        }
    }

    @PUT
    @Path("/seo/{id}")
    public Response updateSeo(@PathParam("id") UUID id, Seo seo) {
        Optional<Seo> optional = seoRepo.findById(id);

        if (optional.isPresent()) {
            Seo existingSeo = optional.get();
            entityCopyUtils.copyProperties(existingSeo, seo);
            Seo updateSeo = seoRepo.save(existingSeo);
            return Response.ok(updateSeo).status(200).build();
        }

        throw new GenericException(400, "No Department with id " + id + " exists");
    }

}
