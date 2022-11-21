package com.eventstoday.api.controller;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.service.impl.CustomersServiceImpl;
import com.eventstoday.api.service.impl.EventsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.eventstoday.api.controller.CustomerControllerTest.asJsonString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EventController.class)
@ActiveProfiles("test")
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventsServiceImpl eventsService;

    @MockBean
    private CustomersServiceImpl customersService;


    private List<Event> eventsList;
    Customer customer = new Customer();

    @BeforeEach
    void setUp(){
        customer.setId(1L);
        Date start = new Date(2022, 3, 2);
        Date end = new Date(2022, 5, 2);
        eventsList = new ArrayList<>();
        eventsList.add(new Event(1L, "Event1", "Lucas", "evento numero 1", "250", start , end, "url", customer));
        eventsList.add(new Event(2L, "Event2", "Lucas", "evento numero 2", "100", start , end, "url", customer));
        eventsList.add(new Event(3L, "Event3", "Lucas", "evento numero 3", "250", start , end, "url", customer));
        eventsList.add(new Event(4L, "Event4", "Lucas", "evento numero 4", "180", start , end, "url", customer));

    }


    @Test
    void findAllEventsTest() throws Exception{
        given(eventsService.getAll()).willReturn(eventsList);
        mockMvc.perform(get("/api/events")).andExpect(status().isOk());
    }

    @Test
    void insertEventTest() throws Exception{
        customer.setId(1L);
        Date start = new Date(2022, 3, 2);
        Date end = new Date(2022, 5, 2);
        Event event = new Event(1L, "Event1", "Lucas", "evento numero 1", "250", start , end, "url", customer);


        //given(customerService.save(customer)).willReturn(customer);
        mockMvc.perform(post("/api/events")
                .content(asJsonString(event))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
    }
    @Test
    void findEventByIdTest() throws Exception{
        Long eventId = 1L;
        customer.setId(1L);
        Date start = new Date(2022, 3, 2);
        Date end = new Date(2022, 5, 2);
        Event event = new Event(1L, "Event1", "Lucas", "evento numero 1", "250", start , end, "url", customer);

        given(eventsService.getById(eventId)).willReturn(Optional.of(event));
        mockMvc.perform(get("/api/events/{id}", event.getId()))
                .andExpect(status().isOk());
    }
}
