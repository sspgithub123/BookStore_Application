package com.bookstoreapplication.repository;

import com.bookstoreapplication.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Ability to provide CRUD operations and create table for given entity
 */
@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration,Integer> {

    @Query(value = "SELECT * FROM user_registration where email=:email_Id", nativeQuery = true)
    Optional<UserRegistration> findByEmailid(String email_Id);
}
