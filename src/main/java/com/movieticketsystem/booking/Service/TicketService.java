package com.movieticketsystem.booking.Service;

import com.movieticketsystem.booking.Entities.Booking;
import com.movieticketsystem.booking.Entities.Ticket;

public interface TicketService {

    Ticket createTicket(String seatNumber, String ticketPrice);
}
