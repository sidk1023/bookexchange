package com.example.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

@Query("SELECT s FROM Users s WHERE s.email = ?1")
Optional<Users> findUserByEmail(String email);

@Query("SELECT s FROM Users s WHERE s.email = ?1")
Users findAndReturnUserByEmail(String email);
}
