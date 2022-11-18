package com.eventstoday.api.repository;

import com.eventstoday.api.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByEmail(String email);
    Customer findByName(String name);
}
