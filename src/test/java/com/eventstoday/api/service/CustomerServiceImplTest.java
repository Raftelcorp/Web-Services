package com.eventstoday.api.service;


import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.repository.ICustomerRepository;
import com.eventstoday.api.service.impl.CustomersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {


    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomersServiceImpl customersService;


    @Test
    public void saveTest() throws Exception {

        Customer customer = new Customer(20L, "Lucas", "upc@gmail.com" , 22, "12345"  );

        given(customerRepository.save(customer)).willReturn(customer);

        Customer savedCustomer = null;
        try{
           savedCustomer= customersService.save(customer);

      }catch(Exception e){

        }
       assertThat(savedCustomer).isNotNull();
        verify(customerRepository).save(any(Customer.class));
    }


    @Test
    public void getAllTest() throws Exception{
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L, "Lucas", "lucas@gmail.com" , 20, "12345"  ));
        list.add(new Customer(2L, "Juan", "juan@gmail.com" , 21, "12345"  ));
        list.add(new Customer(3L, "Marco", "marco@gmail.com" , 22, "12345"  ));
        list.add(new Customer(4L, "Miguel", "miguel@gmail.com" , 23, "12345"  ));

        given(customerRepository.findAll()).willReturn(list);
        List<Customer> listExpected = customersService.getAll();
        assertEquals(listExpected, list);
    }


    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        Customer customer = new Customer(1L, "Lucas", "upc@gmail.com" , 20, "12345"  );

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));
        Optional<Customer> customerExpected = customersService.getById(id);
        assertThat(customerExpected).isNotNull();
        assertEquals(customerExpected, Optional.of(customer));
    }

    @Test
    public void findByEmail() throws Exception{
        String dni ="lucas@gmail.com";
        Customer customer = new Customer(1L, "Lucas", "lucas@gmail.com" , 20, "12345"  );

        given(customerRepository.findByEmail(dni)).willReturn(customer);
        Customer customerExpected = customersService.findByEmail(dni);
        assertThat(customerExpected).isNotNull();
        assertEquals(customerExpected, customer);
    }


    @Test
    public void findByFirstnameAndLastnameTest() throws Exception{
        String name = "Juan";
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L, "Lucas", "lucas@gmail.com" , 20, "12345"  ));
        list.add(new Customer(2L, "Juan", "juan@gmail.com" , 21, "12345"  ));
        list.add(new Customer(3L, "Marco", "marco@gmail.com" , 22, "12345"  ));
        list.add(new Customer(4L, "Miguel", "miguel@gmail.com" , 23, "12345"  ));


        given(customerRepository.findByName(name)).willReturn(list);
        List<Customer> listExpected = customersService.findByName(name);
        assertEquals(listExpected, list);
    }

}
