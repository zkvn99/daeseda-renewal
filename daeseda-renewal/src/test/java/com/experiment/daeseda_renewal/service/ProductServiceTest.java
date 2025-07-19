package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.domain.product.Product;
import com.experiment.daeseda_renewal.domain.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProduct() {

        for(int i=0; i<10; i++){
            // Given
            String productName = "TestProduct" + i;
            BigDecimal productPrice = new BigDecimal("1200");

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
