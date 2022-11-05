package com.eventstoday.api.controller;


import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.entities.Ticket;
import com.eventstoday.api.service.ICustomersService;
import com.eventstoday.api.service.IEventsService;
import com.eventstoday.api.service.ITicketsService;
import io.swagger.annotations.Api;
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
@RequestMapping("/api/tickets")
@Api(tags = "Tickets" , value = "Web service RESTful - Tickets")
public class TicketController {

    private final ICustomersService customersService;
    private final IEventsService eventsService;
    private final ITicketsService ticketsService;

    public TicketController(ICustomersService customersService, IEventsService eventsService, ITicketsService ticketsService) {
        this.customersService = customersService;
        this.eventsService = eventsService;
        this.ticketsService = ticketsService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> findAllTicketsByUserId(@PathVariable("id") Long id) {
        try{
            System.out.println("xdxdxdxdx");
            Optional<Customer> customer = customersService.getById(id);
            System.out.println(customer);
            if(!customer.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            else{
                List<Ticket> tickets = ticketsService.getAll();
                System.out.println(tickets);
               return new ResponseEntity<>(tickets, HttpStatus.OK);
            }

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
