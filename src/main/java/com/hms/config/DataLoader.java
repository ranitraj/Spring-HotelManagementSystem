package com.hms.config;
import com.hms.model.Booking;
import com.hms.service.BookingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(BookingService bookingService) {
        return args -> {
            bookingService.saveBooking(new Booking(null, "John Doe", "Deluxe", new Date(), new Date(), 250));
            bookingService.saveBooking(new Booking(null, "Jane Doe", "Suite", new Date(), new Date(), 375));
        };
    }
}

