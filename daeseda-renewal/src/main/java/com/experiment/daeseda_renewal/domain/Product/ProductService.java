package com.experiment.daeseda_renewal.service.product;

import com.experiment.daeseda_renewal.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product createProduct(String name, BigDecimal price);
}
