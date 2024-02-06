package com.hms.config;
import com.hms.model.Booking;
import com.hms.service.BookingService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(BookingService bookingService) {
        return args -> {
            Pair<Date, Date> firstDatePair = initDates(2);
            bookingService.saveBooking(
                    new Booking(
                            null,
                            "John Doe",
                            "Deluxe",
                            firstDatePair.a,
                            firstDatePair.b,
                            250
                    )
            );

            Pair<Date, Date> secondDatePair = initDates(3);
            bookingService.saveBooking(
                    new Booking(
                            null,
                            "Jane Doe",
                            "Suite",
                            secondDatePair.a,
                            secondDatePair.b,
                            375
                    )
            );
        };
    }

    /**
     * Initializes check-in and check-out dates for a booking.
     * The check-in date is set to the current date, and the check-out date is set two days later.
     * This method demonstrates the conversion from LocalDate to Date for compatibility with older APIs
     * that require java.util.Date objects.
     *
     * @param daysToAdd number of days booking is made for.
     * @return A Pair object containing the check-in date as the first element and the check-out date as the second element.
     */
    private Pair<Date, Date> initDates(int daysToAdd) {
        // Adding daysToAdd to checkInDate as the checkOutDate
        LocalDate checkInDate = LocalDate.now();
        LocalDate checkOutDate = checkInDate.plusDays(daysToAdd);

        // Convert LocalDate back to Date for compatibility
        Date checkIn = Date.from(checkInDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date checkOut = Date.from(checkOutDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return new Pair<>(checkIn, checkOut);
    }
}

