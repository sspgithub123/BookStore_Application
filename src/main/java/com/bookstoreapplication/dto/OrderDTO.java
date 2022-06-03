package com.bookstoreapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Data transfer object of Order Model
 */

@Data
public class OrderDTO {
    private Integer quantity;
    @NotEmpty(message="Please provide address")
    private String address;
    private Integer userId;
    private Integer bookId;
    private boolean cancel;
    private Integer price;
    private LocalDate date;
}