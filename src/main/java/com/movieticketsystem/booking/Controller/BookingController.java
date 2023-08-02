package com.movieticketsystem.booking.Controller;

import com.movieticketsystem.booking.Entities.Booking;
import com.movieticketsystem.booking.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
        Booking newBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @PutMapping("/delete")
    public ResponseEntity<Booking> deleteBooking(@RequestBody Booking booking){
        if(booking.getTickets().isEmpty()){
            Booking msg = bookingService.cancelBooking(booking.getBookingId());
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            Booking updatedBooking = bookingService.cancelBooking(booking.getBookingId(), booking.getTickets());
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        }
    }

    @GetMapping("/bookingHistory/{userId}")
    public ResponseEntity<List<Booking>> getBookingHistoryOfUser(@PathVariable String userId){
        List<Booking> bookingList = bookingService.getBookingHistoryOfUser(userId);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }


}
