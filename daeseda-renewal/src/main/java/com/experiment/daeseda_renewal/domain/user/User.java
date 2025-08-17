package com.experiment.daeseda_renewal.domain.user;

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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;

  @Column
  private String userEmail;

  @Column
  private String userPassword;

  @Column
  private String userName;

  @Column
  private String userNickname;

  @Column
  private String userPhone;

  /**
   * Entity 불변을 위해서는 해당 로직이 존재하면 안됨, 다른 방법으로는
   * 1. 이메일로 조회 이후 가져온 Entity를 활용해서 JpaRepository로 넘겨서 Update를 진행
   * 2. 새로운 Entity를 생성해서 전체 필드 복사 이후 save를 진행
   * 조금 더 고려가 필요할 듯 이렇게 진행하면 도메인 규칙이 도메인 객체 내부에 집중해서 좋음
   */
  public void updatePassword(String userPassword) {
    this.userPassword = userPassword;
  }
}
