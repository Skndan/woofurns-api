# woofurns

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/woofurns-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- RESTEasy Classic's REST Client ([guide](https://quarkus.io/guides/resteasy-client)): Call REST services
- REST Client ([guide](https://quarkus.io/guides/rest-client)): Call REST services
- RESTEasy Classic Multipart ([guide](https://quarkus.io/guides/rest-json#multipart-support)): Multipart support for RESTEasy Classic
- Quarkus Extension for Spring Web API ([guide](https://quarkus.io/guides/spring-web)): Use Spring Web annotations to create your REST services
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and more
- SmallRye JWT ([guide](https://quarkus.io/guides/security-jwt)): Secure your applications with JSON Web Token
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- WebSockets ([guide](https://quarkus.io/guides/websockets)): WebSocket communication channel support
- RESTEasy Classic Qute ([guide](https://quarkus.io/guides/qute)): Qute Templating integration for RESTEasy Classic
- SmallRye JWT Build ([guide](https://quarkus.io/guides/security-jwt-build)): Create JSON Web Token with SmallRye JWT Build API

## Provided Code

### REST Client

Invoke different services through REST with JSON

[Related guide section...](https://quarkus.io/guides/rest-client)


1. Product Module

    Controllers:
        ProductController:
            Create, update, delete, and retrieve products.
        ProductVariantController:
            Manage product variants (e.g., size, color).
        ProductCategoryController:
            Handle product categorization.
        ProductTagController:
            Manage product tags for filtering.

2. Order Module

    Controllers:
        OrderController:
            Create, update, and retrieve orders.
        CartController:
            Handle cart operations (add/remove items, calculate totals).
        CheckoutController:
            Manage the checkout process.
        FulfillmentController:
            Handle order fulfillment and shipping updates.
        RefundController:
            Process refunds for orders.

3. Customer Module

    Controllers:
        CustomerController:
            Manage customer profiles and authentication.
        AddressController:
            Manage customer addresses.
        CustomerGroupController:
            Group customers for segmentation and targeted discounts.

4. Payment Module

    Controllers:
        PaymentController:
            Handle payment creation, updates, and refunds.
        PaymentProviderController:
            Manage integrations with external payment providers.

5. Discount Module

    Controllers:
        DiscountController:
            Create and manage discount codes and rules.
        DiscountConditionController:
            Configure conditions for discounts.

6. Shipping Module

    Controllers:
        ShippingOptionController:
            Manage shipping options and rates.
        ShippingProviderController:
            Integrate with shipping carriers (e.g., UPS, FedEx).
        ShippingProfileController:
            Create shipping profiles for products.

7. Region and Tax Module

    Controllers:
        RegionController:
            Manage regions for shipping and currency settings.
        TaxRateController:
            Configure tax rates for regions.

8. Inventory Module

    Controllers:
        InventoryController:
            Track inventory levels for products and variants.
        StockLocationController:
            Manage stock locations and fulfillment centers.

9. Notification Module

    Controllers:
        NotificationController:
            Manage notifications sent to customers (e.g., emails, SMS).
        EventController:
            Handle events triggering notifications.

10. Analytics Module

    Controllers:
        AnalyticsController:
            Retrieve analytics data and reports.

11. Plugin Module

    Controllers:
        PluginController:
            Manage installed plugins and their configurations.