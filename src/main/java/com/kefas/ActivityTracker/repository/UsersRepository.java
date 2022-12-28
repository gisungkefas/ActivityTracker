package com.kefas.ActivityTracker.repository;


import com.kefas.ActivityTracker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByEmail(String email);

    Users findByEmailAndPassword(String email, String password);
}
