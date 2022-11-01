package com.eventstoday.api.controller;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.service.ICustomersService;
import com.eventstoday.api.service.IEventsService;
import com.eventstoday.api.service.ITicketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final ICustomersService customersService;
    private final IEventsService eventsService;
    private final ITicketsService ticketsService;

    public EventController(ICustomersService customersService, IEventsService eventsService, ITicketsService ticketsService) {
        this.customersService = customersService;
        this.eventsService = eventsService;
        this.ticketsService = ticketsService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> findAllEvents(){

        try{
            List<Event> events = eventsService.getAll();
            if(events.size()>0){
                System.out.println("XDXDXDXDXDX");
                return new ResponseEntity<>(events, HttpStatus.OK);
            }

            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event>findEventById(@PathVariable("id") Long id) {
        try{
            Optional<Event> event = eventsService.getById(id);
            if(!event.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(event.get(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> insertEvent( @PathVariable("id") Long id, @Valid @RequestBody Event event){
        try{
            Optional<Customer> customer = customersService.getById(id);
            if(customer.isPresent()){
                event.setOwnerId(customer.get());
                Event newEvent = eventsService.save(event);
                System.out.println(id);
                System.out.println("XDXDXDXDXDX");
                return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
            }
            else
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
