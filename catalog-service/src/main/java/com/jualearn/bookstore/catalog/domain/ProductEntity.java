package com.jualearn.bookstore.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "products_id_generator")
    @SequenceGenerator(name = "products_id_generator", sequenceName = "products_id_seq")
    private Long id;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Code must not be empty")
    private String code;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    private String description;
    private BigDecimal price;
    private String imageUrl;
}
