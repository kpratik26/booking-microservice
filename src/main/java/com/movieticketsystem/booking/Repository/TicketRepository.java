package com.movieticketsystem.booking.Repository;

import com.movieticketsystem.booking.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
