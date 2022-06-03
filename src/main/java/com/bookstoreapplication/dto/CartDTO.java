package com.bookstoreapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Data transfer object of Cart Model
 */
@Data
public class CartDTO {
    private Integer userId;
    private Integer bookId;
    @NotNull(message="Book quantity yet to be provided")
    private Integer quantity;
}