package com.eventstoday.api.service.impl;


import com.eventstoday.api.entities.Ticket;
import com.eventstoday.api.repository.ITicketRepository;
import com.eventstoday.api.service.ITicketsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TicketsServiceImpl implements ITicketsService {


    private final ITicketRepository ticketRepository;

    public TicketsServiceImpl(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) throws Exception {
         ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> getAll() throws Exception {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getById(Long id) throws Exception {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAllByUserId() throws Exception {
        return ticketRepository.findAllByUserId();
    }

}
