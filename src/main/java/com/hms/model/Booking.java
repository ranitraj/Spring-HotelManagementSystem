package com.hms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @SequenceGenerator(
            name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_sequence"
    )
    private Long id;
    @NotNull(message = "Customer name cannot be null")
    private String customerName;
    @NotNull(message = "Room type cannot be null")

    private String roomType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date checkInDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date checkOutDate;
    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
    private float totalPrice;
}
