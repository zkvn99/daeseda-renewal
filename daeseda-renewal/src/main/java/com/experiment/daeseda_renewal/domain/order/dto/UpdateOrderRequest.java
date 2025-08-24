    package com.experiment.daeseda_renewal.domain.order;

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
    public class OrderDto {
        private Long orderId;

        private LocalDateTime regTime;

        private LocalDateTime modTime;

        private LocalDate deliveryDate;

        private OrderStatus orderStatus;

        private LocalDateTime pickupDate;

        private BigDecimal totalPrice;

        private WashingMethod washingMethod;

        //이친구들은 외래키임
        //일단 연관관계 생략
        //private Long userId;

        //private Long addressId;

    }
