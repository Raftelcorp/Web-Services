package com.eventstoday.api.controller;


import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.entities.Ticket;
import com.eventstoday.api.service.ICustomersService;
import com.eventstoday.api.service.IEventsService;
import com.eventstoday.api.service.ITicketsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Tickets", notes = "Method for listing all Tickets")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Tickets Found"),
            @ApiResponse(code = 404, message = "Tickets not found"),
            @ApiResponse(code = 501, message = "Internal server error"),
    })
    public ResponseEntity<List<Ticket>> findAllTickets(){

        try{
            List<Ticket> tickets = ticketsService.getAll();
            if(tickets.size()>0){
                return new ResponseEntity<>(tickets, HttpStatus.OK);
            }

            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> findAllTicketsByUserId(@PathVariable("id") Long id) {
        try{
            System.out.println("Find all tickets");
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


    @PostMapping(value = ("/{userId}/{eventId}"), consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> insertTicket( @PathVariable("eventId") Long eventId, @PathVariable("userId") Long customerId, @RequestBody Ticket ticket){
        try{
            Optional<Customer> customer = customersService.getById(customerId);
            Optional<Event> event = eventsService.getById(eventId);
            if(customer.isPresent() && event.isPresent()){
                System.out.println("Insert ticket");
                System.out.println(customer);
                System.out.println(event);
                ticket.setOwnerId(customer.get());
                ticket.setEventId(event.get());
                Ticket newTicket = ticketsService.save(ticket);
                return ResponseEntity.status(HttpStatus.CREATED).body(newTicket);
            }
            else
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete ticket by id",notes = "method for delete ticket")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ticket delete"),
            @ApiResponse(code=404, message = "Ticket Not Found"),
            @ApiResponse(code= 501, message = "Internal Server Error")
    })
    public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") Long ticketId){
        System.out.println("Try");
        try{
            Optional<Ticket> ticketDelete=ticketsService.getById(ticketId);
            if(ticketDelete.isPresent()){
                System.out.println("Delete ticket");
                ticketsService.delete(ticketId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
