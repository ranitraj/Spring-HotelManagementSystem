package com.hms.controller;

import com.hms.exceptions.ResourceNotFoundException;
import com.hms.model.Booking;
import com.hms.service.BookingService;
import com.hms.utils.ApiUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrls.API_URL_BOOKINGS)
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Creates a new booking in the system. If the operation is successful, returns the saved booking object.
     * In case of any exception, the global exception handler takes over and returns an appropriate error response.
     *
     * @param booking the booking details from the request body to be saved
     * @return a ResponseEntity containing the saved booking object and HTTP status code
     */
    @PostMapping
    public ResponseEntity<Booking> addBooking(@Validated @RequestBody Booking booking) {
        Booking savedBooking = bookingService.saveBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    /**
     * Handles the HTTP GET request to retrieve a booking by its ID.
     * Returns the booking details if found, otherwise throws a ResourceNotFoundException
     * which is handled globally to return a 404 Not Found response.
     *
     * @param id The ID of the booking to retrieve.
     * @return A ResponseEntity containing the booking if found, with an HTTP 200 OK status.
     */
    @GetMapping(ApiUrls.API_URL_GET_BOOKING_BY_ID)
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking fetchedBooking = bookingService.getBookingById(id);
        return ResponseEntity.ok(fetchedBooking);
    }

    /**
     * Retrieves all bookings. Returns an empty list if no bookings are found.
     * @return ResponseEntity containing a list of bookings or an empty list with HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    /**
     * Updates an existing booking identified by the given ID with the provided booking details.
     * If the booking with the specified ID does not exist, returns a 404 Not Found.
     *
     * @param id The ID of the booking to update.
     * @param booking The updated booking details.
     * @return ResponseEntity containing the updated booking or an error message.
     * @throws ResourceNotFoundException if no Booking is found with the provided ID.
     */
    @PutMapping(ApiUrls.API_URL_UPDATE_BOOKING)
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @Validated @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking)
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with ID: " + id)
                );
    }
}
