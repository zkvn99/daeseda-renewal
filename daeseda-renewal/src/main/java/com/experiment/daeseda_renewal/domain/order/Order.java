package com.experiment.daeseda_renewal.domain.order;

import com.experiment.daeseda_renewal.constant.OrderStatus;
import com.experiment.daeseda_renewal.constant.WashingMethod;
import com.experiment.daeseda_renewal.domain.address.Address;
import com.experiment.daeseda_renewal.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @Enumerated(EnumType.STRING)
    @Column
    private WashingMethod washingMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public void updateFromDto(OrderDto dto) {
        this.modTime = LocalDateTime.now();
        this.deliveryDate = dto.getDeliveryDate();
        this.pickupDate = dto.getPickupDate();
        this.orderStatus = dto.getOrderStatus();
        this.totalPrice = dto.getTotalPrice();
        this.washingMethod = dto.getWashingMethod();
    }
    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;
    }
}
