package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.Booking;
import peaksoft.entity.House;
import peaksoft.repository.BookingRepository;
import peaksoft.service.BookingService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        try {
            bookingRepository.findById(id)
                    .orElseThrow(() -> new NullPointerException("Booking with id: " + id + " is not found"));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }
    @Override
    public void updateBookingById(Long id, Booking booking) {
        try {
            Booking booking1 = bookingRepository.findById(id)
                    .orElseThrow(() -> new NullPointerException("Booking with id: " + id + " is not found"));
            booking1.setHouse(booking.getHouse());
            booking1.setCustomer(booking.getCustomer());
            bookingRepository.save(booking1);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteBookingById(Long id) {
            if (bookingRepository.existsById(id)) {
                bookingRepository.deleteById(id);
            } else throw new NullPointerException("Booking with id: " + id + " is not found");
        }
    }
