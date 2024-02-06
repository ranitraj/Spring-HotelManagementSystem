package com.hms.exceptions;

/**
 * Custom exception class to handle resource not found scenarios.
 * Thrown when a requested resource (e.g., Booking) is not found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
