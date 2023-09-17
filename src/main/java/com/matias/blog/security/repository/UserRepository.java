package com.matias.blog.security.repository;

import com.matias.blog.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameOrEmail(String userName, String email);
    //boolean existByUserName(String userName);
    //boolean existByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
