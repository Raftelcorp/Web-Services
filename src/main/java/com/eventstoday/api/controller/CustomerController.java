package com.eventstoday.api.controller;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.service.ICustomersService;
import com.eventstoday.api.service.IEventsService;
import com.eventstoday.api.service.ITicketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomersService customersService;
    private final IEventsService eventsService;
    private final ITicketsService ticketsService;

    public CustomerController(ICustomersService customersService, IEventsService eventsService, ITicketsService ticketsService) {
        this.eventsService = eventsService;
        this.customersService = customersService;
        this.ticketsService = ticketsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>>findAllCustomers(){

        try{
            List<Customer> customers = customersService.getAll();
            if(customers.size()>0)
                return new ResponseEntity<>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer>findCustomerById(@PathVariable("id") Long id) {
        try{
            Optional<Customer> customer = customersService.getById(id);
            if(!customer.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
