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

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
@Api(tags = "Customers" , value = "Web service RESTful - Customers")
public class CustomerController {

    private final ICustomersService customersService;

    public CustomerController(ICustomersService customersService) {

        this.customersService = customersService;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Customers", notes = "Method for listing all customers")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer Found"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 501, message = "Internal server error"),
    })
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
    @GetMapping(value = "/searchByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findByDni(@PathVariable("email") String email){
        try{
            Customer customer = customersService.findByEmail(email);
            if(customer==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> insertCustomer( @RequestBody Customer customer){
        try{
                System.out.println("Insert");
                Customer newCustomer = customersService.save(customer);
                return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        try{
            Optional<Customer> newCustomer = customersService.getById(id);
            if(!newCustomer.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            if (customer.getName() != null) newCustomer.get().setName(customer.getName());
            if (customer.getEmail() != null) newCustomer.get().setEmail(customer.getEmail());
            if (customer.getAge() !=  0) newCustomer.get().setAge(customer.getAge());
            if (customer.getPassword() != null) newCustomer.get().setPassword(customer.getPassword());
            newCustomer.get().setId(id);

            System.out.println(newCustomer.get());
            customersService.save(newCustomer.get());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete customer by id",notes = "method for delete customer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "customer delete"),
            @ApiResponse(code=404, message = "customer Not Found"),
            @ApiResponse(code= 501, message = "Internal Server Error")
    })
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long customerId){
        System.out.println("Try");
        try{
            Optional<Customer> customerDelete=customersService.getById(customerId);
            if(customerDelete.isPresent()){
                System.out.println("Delete ticket");
                customersService.delete(customerId);
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
