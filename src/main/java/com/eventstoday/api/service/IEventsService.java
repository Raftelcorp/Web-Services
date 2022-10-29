package com.eventstoday.api.service;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;

public interface IEventsService extends CrudService<Event> {

    Event findBytTitle(String title) throws Exception;
    Event findByAuthor(String author) throws Exception;
}
