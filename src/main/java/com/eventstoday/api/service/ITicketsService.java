package com.eventstoday.api.service;

import com.eventstoday.api.entities.Ticket;
import com.eventstoday.api.service.CrudService;

import java.util.List;

public interface ITicketsService extends CrudService<Ticket> {
    List<Ticket> findAllByUserId() throws Exception;
}
