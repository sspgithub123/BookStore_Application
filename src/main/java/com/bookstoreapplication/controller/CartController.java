package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.CartDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.model.Cart;
import com.bookstoreapplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * In Controller class we write the API's here
 */
@RestController
@RequestMapping("/cart")

public class CartController {

    /**
     * Autowired ICartService to inject its dependency here
     */
    @Autowired
    ICartService cartService;

    /**
     * Ability to call api to insert all cart records
     * @param cartdto -represents object of CartDTO class
     * @return -accepted cart information in JSON format
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        Cart newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Ability to call api to retrieve all cart records
     * @return -return all stored data with message
     */
    @GetMapping("/getAll")
    public ResponseDTO getAllCartDetails() {
        ResponseDTO responseDTO = cartService.getCartDetails();
        return responseDTO;
    }

    /**
     * Ability to call api to retrieve cart record by cartId
     * @param cartId -represents cartId
     * @return -return all stored data with message
     */
    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId){
        Optional<Cart> specificCartDetail=cartService.getCartDetailsById(cartId);
        ResponseDTO responseDTO=new ResponseDTO("Cart details retrieved successfully",specificCartDetail);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    /**
     * Ability to call api to retrieve cart record by book id
     * @param bookID -represents bookId
     * @return -return all stored data with message
     */
    @GetMapping("/retrieveCartByBookId/{bookID}")
    public ResponseEntity<ResponseDTO> getCartRecordByBookId(@PathVariable Integer bookID){
        Cart newCart = cartService.getCartRecordByBookId(bookID);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newCart);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * Ability to call api to delete cart by id
     * @param cartId -represents cartId
     * @return -return all stored data with message by deleting this cartId
     */
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        Optional<Cart> delete = cartService.deleteCartItemById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", delete);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to call api to update cart by id
     * @param cartId -represents cartId
     * @param cartDTO -represents object of CartDTO class
     * @return -return updated information
     */
    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable Integer cartId,@Valid @RequestBody CartDTO cartDTO){
        Cart updateRecord = cartService.updateRecordById(cartId,cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

}
