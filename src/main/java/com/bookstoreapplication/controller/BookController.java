package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.BookDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.model.Book;
import com.bookstoreapplication.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * In Controller class we write the API's here
 */
@RestController
@RequestMapping("/book")
public class BookController {

    /**
     * Autowired IBookService to inject its dependency here
     */
    @Autowired
    IBookService bookService;

    /**
     * Ability to call api to insert Book record
     * @param bookDTO- represents object of BookDTO class
     * @return- accepted book information in JSON format
     * @apiNote accepts the data in JSON format and stores it in DB
     */
    @PostMapping("/insert")
    public ResponseEntity<String> addBookToRepository(@Valid @RequestBody BookDTO bookDTO){
        Book newBook= bookService.createBook(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("New Book Added in Book Store",newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Ability to call api to retrieve all book records
     * @return- return all stored data with message
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllBookData()
    {
        List<Book> listOfBooks = bookService.getAllBookData();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * Ability to call api to get record by BookId
     * @param BookId -represents BookId
     * @return -return all stored data with message
     */
    @GetMapping(value = "/getBy/{BookId}")
    public ResponseEntity<String> getBookDataById(@PathVariable int BookId)
    {
        Optional<Book> Book = bookService.getBookDataById(BookId);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id (:",Book);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * Ability to call api to delete book record by BookId
     * @param BookId -represents BookId
     * @return -return all stored data with message by deleting this bookId
     */
    @DeleteMapping("/delete/{BookId}")
    public ResponseEntity<String> deleteRecordById(@PathVariable int BookId){
        ResponseDTO dto = new ResponseDTO("Book Record deleted successfully", bookService.deleteRecordById(BookId));
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * Ability to call api to update book record by BookId
     * @param BookId -represents BookId
     * @param bookDTO represents object of BookDTO class
     * @return -return updated information
     */
    @PutMapping("/updateBookById/{BookId}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer BookId,@Valid @RequestBody BookDTO bookDTO){
        Book updateRecord = bookService.updateRecordById(BookId,bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
}