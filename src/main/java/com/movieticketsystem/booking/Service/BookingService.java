package com.movieticketsystem.booking.Service;

import com.movieticketsystem.booking.Entities.Booking;
import com.movieticketsystem.booking.Entities.Ticket;

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getBookingHistoryOfUser(String userId);
    Booking cancelBooking(String bookingId);
    Booking cancelBooking(String bookingId, List<Ticket> ticketsToDelete);
}
