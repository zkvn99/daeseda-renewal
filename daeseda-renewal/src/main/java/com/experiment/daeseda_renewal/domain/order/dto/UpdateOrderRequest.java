    package com.experiment.daeseda_renewal.domain.order.dto;

    import com.experiment.daeseda_renewal.constant.OrderStatus;
    import com.experiment.daeseda_renewal.constant.WashingMethod;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Getter;

    import java.math.BigDecimal;
    import java.time.LocalDate;
    import java.time.LocalDateTime;

    @Getter
    @Builder
    @AllArgsConstructor
    public class UpdateOrderRequest {
        private Long orderId;

        private LocalDateTime regTime;

        private LocalDateTime modTime;

        private LocalDate deliveryDate;

        private OrderStatus orderStatus;

        private LocalDateTime pickupDate;

        private BigDecimal totalPrice;

        private WashingMethod washingMethod;

    }
