package com.bookstoreapplication.controller;

import com.bookstoreapplication.dto.OrderDTO;
import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.model.Order;
import com.bookstoreapplication.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * In Controller class we write the API's here
 */
@RestController
@RequestMapping("/order")

public class OrderController {

    /**
     * Autowired IOrderService dependency to inject its dependecy here
     */
    @Autowired
    private IOrderService orderService;

    /**
     * Ability to call api to insert order record
     *
     * @param orderdto -represents object of OrderDTO class
     * @return -accepted order information in JSON format
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto) {
        Order newOrder = orderService.insertOrder(orderdto);
        ResponseDTO dto = new ResponseDTO("Order placed successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    /**
     * Ability to call api retrieve all order records
     *
     * @return -return all stored data with message
     */
    @GetMapping("/retrieveAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrderRecords() {
        List<Order> newOrder = orderService.getAllOrderRecords();
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * Ability to call api to retrieve order records by id
     *
     * @param id -represents id
     * @return -return all stored data with message
     */
    @GetMapping("/retrieveOrder/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id) {
        Order newOrder = orderService.getOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * Ability to call api to update order record by id
     *
     * @param id       -represents id
     * @param orderdto -represents object of OrderDTO class
     * @return -return updated information
     */
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id, @Valid @RequestBody OrderDTO orderdto) {
        Order newOrder = orderService.updateOrderRecord(id, orderdto);
        ResponseDTO dto = new ResponseDTO("Record updated successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    /**
     * Ability to call api to delete order record by id
     *
     * @param id -represents id
     * @return -return all stored data with message by deleting this id
     */
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderRecord(@PathVariable Integer id) {
        Order newOrder = orderService.deleteOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    /**
     * Ability to call api to cancelOrder record by id
     * @param id -represents order Id
     * @return -returns order record which we have cancelled
     */
    @GetMapping("/cancelOrder/{id}")
    public ResponseEntity<ResponseDTO> cancelOrderRecord(@PathVariable Integer id) {
        Order newOrder = orderService.CancelOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Order Cancelled successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

}