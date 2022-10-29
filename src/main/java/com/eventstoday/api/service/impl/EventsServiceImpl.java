package com.eventstoday.api.service.impl;



import com.eventstoday.api.entities.Event;
import com.eventstoday.api.repository.IEventRepository;
import com.eventstoday.api.service.IEventsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class EventsServiceImpl implements IEventsService {


    private final IEventRepository eventRepository;

    public EventsServiceImpl(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public Event save(Event event) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public List<Event> getAll() throws Exception {
        return null;
    }

    @Override
    public Optional<Event> getById(Long id) throws Exception {
        return Optional.empty();
    }

    @Override
    public Event findBytTitle(String title) throws Exception {
        return null;
    }

    @Override
    public Event findByAuthor(String author) throws Exception {
        return null;
    }
}
