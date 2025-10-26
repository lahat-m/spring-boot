package com.jualearn.bookstore.catalog.domain;

import com.jualearn.bookstore.catalog.domain.records.Product;

class ProductMapper {
    static Product toProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice()
        );
    }
}