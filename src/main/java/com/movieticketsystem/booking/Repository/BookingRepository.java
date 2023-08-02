package com.movieticketsystem.booking.Repository;

import com.movieticketsystem.booking.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findAllByUserId(String userId);
}
