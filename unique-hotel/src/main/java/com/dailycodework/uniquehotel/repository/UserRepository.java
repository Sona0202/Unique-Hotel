package com.dailycodework.uniquehotel.repository;

import com.dailycodework.uniquehotel.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    Optional<Users> findByEmail(String email);
}
