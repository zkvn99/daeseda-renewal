package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.entity.Product;
import com.experiment.daeseda_renewal.repository.ProductRepository;
import com.experiment.daeseda_renewal.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @Test
    public void testCreateProduct() {

        for(int i=0; i<10; i++){
            // Given
            String productName = "TestProduct" + i;
            BigDecimal productPrice = new BigDecimal("1200" + i);

            // When
            Product savedProduct = productService.createProduct(productName, productPrice);

            // Then
            assertThat(savedProduct).isNotNull();
            assertThat(savedProduct.getId()).isNotNull();
            assertThat(savedProduct.getName()).isEqualTo(productName);
            assertThat(savedProduct.getPrice()).isEqualByComparingTo(productPrice);
        }
    }

}
