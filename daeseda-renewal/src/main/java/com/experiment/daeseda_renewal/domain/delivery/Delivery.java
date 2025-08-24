package com.experiment.daeseda_renewal.domain.delivery;

import com.experiment.daeseda_renewal.constant.DeliveryStatus;
import com.experiment.daeseda_renewal.domain.address.Address;
import com.experiment.daeseda_renewal.domain.order.Order;
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

@Entity
@Table(name = "delivery")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryId;

    @Enumerated(EnumType.STRING)
    @Column
    private DeliveryStatus deliveryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
