package com.movieticketsystem.booking.Service;

import com.movieticketsystem.booking.Entities.Booking;
import com.movieticketsystem.booking.Entities.Ticket;
import com.movieticketsystem.booking.Repository.BookingRepository;
import com.movieticketsystem.booking.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public Booking createBooking(Booking booking) {
        List<Ticket> ticketList = new ArrayList<>();
        String ticketPrice= "100";
        Booking newBooking = new Booking();
        newBooking.setShowDate(booking.getShowDate());
        newBooking.setShowTime(booking.getShowTime());
        newBooking.setMovieId(booking.getMovieId());
        newBooking.setScreenId(booking.getScreenId());
        newBooking.setLanguage(booking.getLanguage());
        newBooking.setPaymentMethod(booking.getPaymentMethod());
        newBooking.setBookingDateTime(LocalDateTime.now());
        newBooking.setUserId(booking.getUserId());
        for(Ticket seat : booking.getTickets()){
            Ticket ticket = ticketService.createTicket(seat.getSeatNumber(), ticketPrice);
            ticketList.add(ticket);
        }
        newBooking.setTickets(ticketList);
        return bookingRepository.save(newBooking);
    }

    @Override
    public List<Booking> getBookingHistoryOfUser(String userId) {
        List<Booking> bookingList = bookingRepository.findAllByUserId(userId);
        return bookingList;
    }

    @Override
    public Booking cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.getTickets().forEach(ticket -> ticket.setStatus("cancelled"));
        /* to-do
        call seat service to make it available again
         */
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(String bookingId, List<Ticket> ticketsToCancel) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        List<String> seatNumbers = new ArrayList<>();
        List<String> ticketsToCancelById = new ArrayList<>();
        for(Ticket ticketToCancel : ticketsToCancel){
            seatNumbers.add(ticketToCancel.getSeatNumber());
        }
        booking.getTickets().forEach(ticket ->
                {
                    if(seatNumbers.contains(ticket.getSeatNumber())){
                        ticket.setStatus("cancelled");
                    }
                }
                );
        return bookingRepository.save(booking);
    }
}
