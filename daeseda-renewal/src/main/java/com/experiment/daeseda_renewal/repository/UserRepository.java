package com.experiment.daeseda_renewal.repository;

import com.experiment.daeseda_renewal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
