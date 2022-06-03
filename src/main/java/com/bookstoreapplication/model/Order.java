package com.bookstoreapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The @Entity annotation specifies that the class is an entity and is mapped to a database table
 */
@Entity
@Data
@Table(name="orderDetails")
public class Order {
    @Id
    @GeneratedValue
    private Integer orderID;
    private LocalDate date = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    @ManyToOne
    @JoinColumn(name="userId")
    private UserRegistration user;
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;
    private boolean cancel;

    public Order(Integer orderID,Integer price, Integer quantity, String address, Book book, UserRegistration user, boolean cancel) {
        this.orderID = orderID;
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }

    public Order() {
        super();
    }

    public Order(Integer price, Integer quantity, String address, Book book, UserRegistration user, boolean cancel) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }
}