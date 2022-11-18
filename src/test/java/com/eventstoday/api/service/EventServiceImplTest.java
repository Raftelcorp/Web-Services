package com.eventstoday.api.service;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.repository.ICustomerRepository;
import com.eventstoday.api.repository.IEventRepository;
import com.eventstoday.api.service.impl.CustomersServiceImpl;
import com.eventstoday.api.service.impl.EventsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {



    @Mock
    private IEventRepository eventRepository;

    @InjectMocks
    private EventsServiceImpl eventsService;


    Customer customer = new Customer();


    @Test
    public void saveTest() throws Exception {

        customer.setId(1L);
        Date start = new Date(2022, 3, 2);
        Date end = new Date(2022, 5, 2);

        Event event = new Event(1L, "Event1", "Lucas", "evento numero 1", "250", start , end, "url", customer);

        given(eventRepository.save(event)).willReturn(event);

        Event savedEvent = null;
        try{
            savedEvent= eventsService.save(event);

        }catch(Exception e){

        }
        assertThat(savedEvent).isNotNull();
        verify(eventRepository).save(any(Event.class));
    }

    @Test
    public void getAllTest() throws Exception{

        customer.setId(1L);
        Date start = new Date(2022, 3, 2);
        Date end = new Date(2022, 5, 2);

        List<Event> list = new ArrayList<>();
        list.add(new Event(1L, "Event1", "Lucas", "evento numero 1", "20", start , end, "url", customer));
        list.add(new Event (2L, "Event2", "Lucas", "evento numero 2", "100", start , end, "url", customer));
        list.add(new Event (3L, "Event3", "Lucas", "evento numero 3", "250", start , end, "url", customer));
        list.add(new Event(4L, "Event4", "Lucas", "evento numero 4", "180", start , end, "url", customer));

        given(eventRepository.findAll()).willReturn(list);
        List<Event> listExpected = eventsService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        customer.setId(1L);
        Date start = new Date(2022, 3, 2);
        Date end = new Date(2022, 5, 2);

        Event event = new Event(1L, "Event1", "Lucas", "evento numero 1", "20", start , end, "url", customer);

        given(eventRepository.findById(id)).willReturn(Optional.of(event));
        Optional<Event> eventExpected = eventsService.getById(id);
        assertThat(eventExpected).isNotNull();
        assertEquals(eventExpected, Optional.of(event));
    }

}
