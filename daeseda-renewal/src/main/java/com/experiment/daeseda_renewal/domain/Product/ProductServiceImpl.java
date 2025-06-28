package com.experiment.daeseda_renewal.domain.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, BigDecimal price) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .build();
        return productRepository.save(product);
    }
}
