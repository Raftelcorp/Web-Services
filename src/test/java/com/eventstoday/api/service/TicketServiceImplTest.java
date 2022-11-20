package com.eventstoday.api.service;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.entities.Ticket;
import com.eventstoday.api.service.impl.TicketsServiceImpl;
import com.eventstoday.api.repository.ITicketRepository;
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
public class TicketServiceImplTest {


    @Mock
    private ITicketRepository ticketRepository;

    @InjectMocks
    private TicketsServiceImpl ticketsService;

    Customer customer = new Customer(20L, "Lucas", "upc@gmail.com" , 22, "12345"  );
    Event event = new Event();

    @Test
    public void saveTest() throws Exception {

        customer.setId(1L);
        event.setId(1L);

        Ticket ticket = new Ticket(1L,  customer, event);

        given(ticketRepository.save(ticket)).willReturn(ticket);

        Ticket savedTicket = null;
        try{
            savedTicket= ticketsService.save(ticket);

        }catch(Exception e){

        }
        assertThat(savedTicket).isNotNull();
        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test
    public void getAllTest() throws Exception{

        customer.setId(1L);
        event.setId(1L);

        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket(1L, customer , event));
        list.add(new Ticket(2L, customer , event));
        list.add(new Ticket(3L, customer , event));
        list.add(new Ticket(4L, customer , event));


        given(ticketRepository.findAll()).willReturn(list);
        List<Ticket> listExpected = ticketsService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        customer.setId(1L);
        event.setId(1L);

        Ticket ticket = new Ticket(1L,  customer, event);

        given(ticketRepository.findById(id)).willReturn(Optional.of(ticket));
        Optional<Ticket> eventExpected = ticketsService.getById(id);
        assertThat(eventExpected).isNotNull();
        assertEquals(eventExpected, Optional.of(ticket));
    }
}
