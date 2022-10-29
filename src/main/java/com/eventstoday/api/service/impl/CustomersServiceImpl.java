package com.eventstoday.api.service.impl;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.repository.ICustomerRepository;
import com.eventstoday.api.service.ICustomersService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomersServiceImpl implements ICustomersService {
    private final ICustomerRepository customerRepository;

    public CustomersServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByEmail(String email) throws Exception {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer findByName(String name) throws Exception {
        return customerRepository.findByName(name);
    }
}
