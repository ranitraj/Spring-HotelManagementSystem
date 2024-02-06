package com.hms.service;

import com.hms.model.Booking;
import com.hms.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Saves/ Updates a booking to the database.
     * This method can handle both the creation of a new booking and the updating of an existing booking.
     * If the booking has an ID, it updates the existing booking; otherwise, it creates a new booking.
     *
     * @param booking the booking entity to be saved or updated.
     * @return the saved or updated booking entity with its ID populated.
     */
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
