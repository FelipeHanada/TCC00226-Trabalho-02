package com.allberfelipe.trabalho_02.repository;

import com.allberfelipe.trabalho_02.model.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u where u.id = :id")
    Optional<User> findByIdWithLock(@Param("id") Long id);

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByEmailAndPasswordHash(@Param("email") String email, @Param("passwordHash") String passwordHash);
}
