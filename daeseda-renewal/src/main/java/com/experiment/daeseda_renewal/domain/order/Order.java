package com.experiment.daeseda_renewal.domain.order;


import com.experiment.daeseda_renewal.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(nullable = false)
    private LocalDateTime regTime;

    @Column
    private LocalDateTime modTime;

    @Column
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

    @Column
    private LocalDateTime pickupDate;

    @Column
    private BigDecimal totalPrice;

    @Column
    private String washingMethod;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;*/

   /* @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();*/

   /* public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }*/
}
