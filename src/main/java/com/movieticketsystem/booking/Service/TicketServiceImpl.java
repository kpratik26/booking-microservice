package com.movieticketsystem.booking.Service;

import com.movieticketsystem.booking.Entities.Booking;
import com.movieticketsystem.booking.Entities.Ticket;
import com.movieticketsystem.booking.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public Ticket createTicket(String seatNumber, String ticketPrice) {
        Ticket ticket = new Ticket();
        ticket.setSeatNumber(seatNumber);
        ticket.setTicketPrice(ticketPrice);
        ticket.setStatus("active");
        return ticketRepository.save(ticket);
    }
}
