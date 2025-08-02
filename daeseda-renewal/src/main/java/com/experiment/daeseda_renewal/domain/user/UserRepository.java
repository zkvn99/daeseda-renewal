package com.experiment.daeseda_renewal.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByName(String name);

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

}
