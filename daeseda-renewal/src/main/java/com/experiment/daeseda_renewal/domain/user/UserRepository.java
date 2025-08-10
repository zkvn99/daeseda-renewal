package com.experiment.daeseda_renewal.domain.user;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUserEmail(String userEmail);

  Optional<User> findByUserEmail(String userEmail);

  @Query("SELECT u.userEmail FROM User u WHERE u.userName = :userName AND u.userPhone = :userPhone")
  Optional<String> findUserEmail(@Param("userName") String userName,
      @Param("userPhone") String userPhone);

  boolean existsByUserNameAndUserEmailAndUserPhone(String userName, String userEmail,
      String userPhone);

}
