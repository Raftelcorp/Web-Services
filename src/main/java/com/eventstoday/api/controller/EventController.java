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
@RequestMapping("/api/events")
@Api(tags = "Events" , value = "Web service RESTful - Events")
public class EventController {

    private final ICustomersService customersService;
    private final IEventsService eventsService;
    //private final ITicketsService ticketsService;

    public EventController(ICustomersService customersService, IEventsService eventsService) {
        this.customersService = customersService;
        this.eventsService = eventsService;
       // this.ticketsService = ticketsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Events", notes = "Method for listing all events")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Event Found"),
            @ApiResponse(code = 404, message = "Event not found"),
            @ApiResponse(code = 501, message = "Internal server error"),
    })
    public ResponseEntity<List<Event>> findAllEvents(){

        try{
            List<Event> events = eventsService.getAll();
            if(events.size()>0){
                return new ResponseEntity<>(events, HttpStatus.OK);
            }

            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
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

    @PostMapping(value = ("/{id}"), consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> insertEvent( @PathVariable("id") Long id, @RequestBody Event event){
        try{
            Optional<Customer> customer = customersService.getById(id);
            if(customer.isPresent()){
                System.out.println("Insert");
                System.out.println(customer);
                event.setOwnerId(customer.get());
                Event newEvent = eventsService.save(event);
                return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
            }

            else
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Event by id",notes = "method for delete Event")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Event delete"),
            @ApiResponse(code=404, message = "Event Not Found"),
            @ApiResponse(code= 501, message = "Internal Server Error")
    })
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long eventId){
        System.out.println("Try");
        try{
            Optional<Event> eventDelete=eventsService.getById(eventId);
            if(eventDelete.isPresent()){
                System.out.println("Delete ticket");
                eventsService.delete(eventId);
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
