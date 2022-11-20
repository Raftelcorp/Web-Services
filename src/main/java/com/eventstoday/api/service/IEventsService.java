package com.eventstoday.api.service;

import com.eventstoday.api.entities.Event;
import com.eventstoday.api.service.CrudService;

public interface IEventsService extends CrudService<Event> {

    Event findBytTitle(String title) throws Exception;
    Event findByAuthor(String author) throws Exception;


}
