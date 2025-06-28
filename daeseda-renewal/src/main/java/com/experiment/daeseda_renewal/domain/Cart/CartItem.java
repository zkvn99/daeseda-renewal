package com.experiment.daeseda_renewal.domain.Cart;

import com.experiment.daeseda_renewal.domain.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int quantity;
}
