package com.bookstoreapplication.repository;

import com.bookstoreapplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Ability to provide CRUD operations and create table for given entity
 */

public interface BookStoreCartRepository extends JpaRepository<Cart,Integer> {

    @Query(value="select * from cart where book_id =:bookId",nativeQuery =true)
    Optional<Cart> findByBookId(Integer bookId);
}
