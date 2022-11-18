package com.eventstoday.api.controller;


import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.service.impl.CustomersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("test")
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomersServiceImpl customerService;

    private List<Customer> customerList;

    @BeforeEach
    void setUp(){
        customerList = new ArrayList<>();
        customerList.add(new Customer(1L, "Lucas", "lucas@gmail.com" , 20, "12345"  ));
        customerList.add(new Customer(2L, "Juan", "juan@gmail.com" , 21, "12345"  ));
        customerList.add(new Customer(3L, "Marco", "marco@gmail.com" , 22, "12345" ));
        customerList.add(new Customer(4L, "Miguel", "miguel@gmail.com" , 23, "12345"  ));

    }


    @Test
    void findAllCustomersTest() throws Exception{
        given(customerService.getAll()).willReturn(customerList);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }


    @Test
    void findCustomerByIdTest() throws Exception{
        Long customerId = 1L;
        Customer customer = new Customer(1L, "Lucas", "lucas@gmail.com" , 20, "12345");

        given(customerService.getById(customerId)).willReturn(Optional.of(customer));
        mockMvc.perform(get("/api/customers/{id}", customer.getId()))
                .andExpect(status().isOk());
    }


    @Test
    void insertCustomerTest() throws Exception{
        Customer customer = new Customer(1L, "Lucas", "lucas@gmail.com" , 20, "12345");

        //given(customerService.save(customer)).willReturn(customer);
        mockMvc.perform(post("/api/customers")
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
    }

    @Test
    void updateCustomerTest() throws Exception{
        Long id = 1L;
        Customer customer = new Customer(1L, "Lu", "lucas@gmail.com" , 20, "12345");

        given(customerService.getById(id)).willReturn(Optional.of(customer));
        mockMvc.perform(patch("/api/customers/{id}", id)
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }




    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
