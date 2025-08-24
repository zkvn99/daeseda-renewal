package com.experiment.daeseda_renewal.domain.delivery;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findByOrderOrderId(Long orderId);
}
