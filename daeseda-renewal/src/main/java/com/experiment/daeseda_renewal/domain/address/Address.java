package com.experiment.daeseda_renewal.domain.address;

import com.experiment.daeseda_renewal.global.jpa.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@Table(name = "addresses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long addressId;

  @Column
  private String addressName;

  @Column
  private String addressDetail;

  @Column
  private String addressZipcode;

  @Column
  private String addressRoad;

  @Column
  private Long userId;

  @Column
  private boolean defaultAddress;
}
