package com.bookstoreapplication.model;

import lombok.Data;

import javax.persistence.*;

/**
 * The @Entity annotation specifies that the class is an entity and is mapped to a database table
 */
@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Integer cartId;

    /**
     * @ManyToOne mapping means that one parent record can have multiple child records.
     * In other words, multiple records of a table can associate themselves with a common record in another table.
     * @JoinColumn is used to specify a column for joining an entity association
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserRegistration user;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    private Integer quantity;

    public Cart(Integer cartId, Integer quantity, Book book, UserRegistration user) {
        super();
        this.cartId = cartId;
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }

    public Cart(Integer quantity, Book book, UserRegistration user) {
        super();
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }

    public Cart() {
        super();
    }
}