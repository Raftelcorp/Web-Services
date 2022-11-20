package com.eventstoday.api.repository;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {

    Optional<Ticket> findById(Long Id);
    @Query("SELECT ticket FROM Ticket ticket")
    List<Ticket> findAllByUserId();

}
