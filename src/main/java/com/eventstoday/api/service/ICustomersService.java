package com.eventstoday.api.service;

import com.eventstoday.api.entities.Customer;

import java.util.List;

public interface ICustomersService extends CrudService<Customer> {


    Customer findByEmail(String email) throws Exception;
    List<Customer> findByName(String name)  throws Exception;


}
