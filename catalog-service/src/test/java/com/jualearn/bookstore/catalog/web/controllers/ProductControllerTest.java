package com.jualearn.bookstore.catalog.web.controllers;

import com.jualearn.bookstore.catalog.AbstractIT;
import com.jualearn.bookstore.catalog.domain.records.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@Sql(scripts = "/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void ShouldReturnProducts() {
     given().contentType(ContentType.JSON)
             .when()
             .get("/api/products")
             .then()
             .statusCode(200)
             .body("data", hasSize(10))
             .body("totalElements", is(15))
             .body("pageNumber", is(1))
             .body("totalPages", is(2))
             .body("isFirst", is(true))
             .body("isLast", is(false))
             .body("hasNext", is(true))
             .body("hasPrevious", is(false));
    }

    @Test
    void ShouldReturnProductByCode(){
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);
    }

    @Test
    void ShouldReturnNotFoundWhenProductCodeNotExists(){
        String code = "invalid_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with " +code+ " not found."));

    }
}