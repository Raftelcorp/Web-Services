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
    @Transactional
    public Event save(Event event) throws Exception {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        eventRepository.deleteById(id);

    }

    @Override
    public List<Event> getAll() throws Exception {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getById(Long id) throws Exception {
        return eventRepository.findById(id);
    }

    @Override
    public Event findBytTitle(String title) throws Exception {
        return eventRepository.findByTitle(title);
    }

    @Override
    public Event findByAuthor(String author) throws Exception {
        return eventRepository.findByAuthor(author);
    }



}
