package com.jualearn.bookstore.catalog.web.controllers;

import com.jualearn.bookstore.catalog.domain.records.PageResult;
import com.jualearn.bookstore.catalog.domain.records.Product;
import com.jualearn.bookstore.catalog.domain.ProductNotFoundException;
import com.jualearn.bookstore.catalog.domain.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;
    @GetMapping
    PageResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productService.getProductByCode(code)
        .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
