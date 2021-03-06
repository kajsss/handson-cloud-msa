package com.samsungsds.eshop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPw(String email, String pw);

    Optional<User> findByEmail(String email);
}