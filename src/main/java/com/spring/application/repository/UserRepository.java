package com.spring.application.repository;

import com.spring.application.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String username);

    Boolean existsByName(String username);

}
