package com.jualearn.bookstore.catalog.domain;

import com.jualearn.bookstore.catalog.ApplicationProperties;
import com.jualearn.bookstore.catalog.domain.records.PageResult;
import com.jualearn.bookstore.catalog.domain.records.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional()
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;
    public ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PageResult<Product> getProducts(int pageNo) {
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
        Page<Product> productsPage = productRepository.findAll(pageable).map(ProductMapper::toProduct);

        return new PageResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }


    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
