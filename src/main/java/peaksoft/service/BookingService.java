package peaksoft.service;

import peaksoft.entity.Booking;

import java.util.List;

public interface BookingService {
    void saveBooking(Booking booking);

    Booking getBookingById(Long id);

    List<Booking> getAllBooking();

    void updateBookingById(Long id, Booking booking);

    void deleteBookingById(Long id);
}