package com.experiment.daeseda_renewal.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@Table(name = "addresses")
@NoArgsConstructor
public class Address {
}
