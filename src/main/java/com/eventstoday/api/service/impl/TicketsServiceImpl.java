package com.eventstoday.api.service.impl;


import com.eventstoday.api.entities.Ticket;
import com.eventstoday.api.service.ITicketsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TicketsServiceImpl implements ITicketsService {
    @Override
    public Ticket save(Ticket ticket) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public List<Ticket> getAll() throws Exception {
        return null;
    }

    @Override
    public Optional<Ticket> getById(Long id) throws Exception {
        return Optional.empty();
    }
}
