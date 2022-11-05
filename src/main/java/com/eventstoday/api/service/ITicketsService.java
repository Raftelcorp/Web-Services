package com.eventstoday.api.service;

import com.eventstoday.api.entities.Ticket;

import java.util.List;

public interface ITicketsService extends CrudService<Ticket>{
    List<Ticket> findAllByUserId() throws Exception;
}
