package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.CartDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.exception.BookStoreException;
import com.bookstoreapplication.model.Book;
import com.bookstoreapplication.model.Cart;
import com.bookstoreapplication.model.UserRegistration;
import com.bookstoreapplication.repository.BookStoreCartRepository;
import com.bookstoreapplication.repository.BookStoreRepository;
import com.bookstoreapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j

/**
 * Created CartService class to serve api calls done by controller layer
 */
public class CartService implements ICartService {

    /**
     * Autowired interfaces to inject its dependency here
     */
    @Autowired
    BookStoreRepository bookStoreRepository;
    @Autowired
    UserRegistrationRepository userRegistrationRepository;
    @Autowired
    BookStoreCartRepository bookStoreCartRepository;

    /**
     * create a method name as insertItems
     * Ability to save cart details to repository
     *
     * @param cartdto - cart data
     * @return - save all data
     */
    public Cart insertItems(CartDTO cartdto) {
        Optional<Book> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> user = userRegistrationRepository.findById(cartdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (cartdto.getQuantity()<= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - cartdto.getQuantity();
                Cart newCart = new Cart(cartdto.getQuantity(), book.get(), user.get());
                bookStoreCartRepository.save(newCart);
                return newCart;
            } else {
                throw new BookStoreException("Requested quantity is not available");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    /**
     * create a method name as getCartDetails
     * - Ability to get all cart' data by findAll() method
     * @return - all data
     */
    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails=bookStoreCartRepository.findAll();
        ResponseDTO dto= new ResponseDTO();
        if (getCartDetails.isEmpty()){
            String message=" Not found Any Cart details ";
            dto.setMessage(message);
            dto.setData(0);
            return dto;

        }
        else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }

    /**
     * create a method name as getCartDetailsById
     * - Ability to get cart data by cartId
     * @param cartId - cart id
     * @return - cart data by id
     */
    @Override
    public Optional<Cart> getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData=bookStoreCartRepository.findById(cartId);
        if (getCartData.isPresent()){
            return getCartData;
        }
        else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    /**
     * create a method name as getCartRecordByBookId
     * - Ability to get cart data by bookId
     * @param bookId - bookID
     * @return - cart data by book id
     */
    public Cart getCartRecordByBookId(Integer bookId) {
        Optional<Cart> cart = bookStoreCartRepository.findByBookId(bookId);
        if(cart.isEmpty()) {
            return null;
            //throw new BookStoreException("Cart Record doesn't exists");
        }
        else {
            log.info("Cart record retrieved successfully for book id "+bookId);
            return cart.get();
        }
    }

    /**
     * create a method name as deleteCartItemById
     * ability to delete data by particular cart id
     * @param cartId - cart id
     * @return - cartId and Acknowledgment message
     */
    @Override
    public Optional<Cart> deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData=bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()){
            bookStoreCartRepository.deleteById(cartId);
            return deleteData;
        }
        else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }

    }

    /**
     * create a method name as updateRecordById
     * Ability to update cart data for particular id
     * @param cartId - cart id
     * @param cartDTO - cart data
     * @return - updated cart information in JSON format
     */
    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = bookStoreCartRepository.findById(cartId);
        Optional<Book>  book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRegistrationRepository.findById(cartDTO.getUserId());
        if(cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        }
        else {
            if(book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId,cartDTO.getQuantity(),book.get(),user.get());
                bookStoreCartRepository.save(newCart);
                log.info("Cart record updated successfully for id "+cartId);
                return newCart;
            }
            else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        }
    }
}
