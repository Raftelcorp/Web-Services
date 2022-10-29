package com.eventstoday.api.repository;

import com.eventstoday.api.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<Event,Long> {

    Event findBytTitle(String title);
    Event findByAuthor(String author);
}
