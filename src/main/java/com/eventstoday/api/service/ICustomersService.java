package com.eventstoday.api.service;

import com.eventstoday.api.entities.Customer;

public interface ICustomersService extends CrudService<Customer> {


    Customer findByEmail(String email) throws Exception;
    Customer findByName(String name)  throws Exception;
}
