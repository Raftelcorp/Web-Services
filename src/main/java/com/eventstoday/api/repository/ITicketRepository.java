package com.eventstoday.api.repository;

import com.eventstoday.api.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {

    Optional<Ticket> findById(Long Id);

}
