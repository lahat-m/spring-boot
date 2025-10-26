package com.jualearn.bookstore.catalog.domain.records;

import java.math.BigDecimal;

public record Product(
        String code,
        String name,
        String description,
        String imageUrl,
        BigDecimal price
) {
}
