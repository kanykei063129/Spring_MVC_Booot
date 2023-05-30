package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.service.BookingService;
import peaksoft.service.CustomerService;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingApi {
    private final BookingService bookingService;
    private final CustomerService customerService;
    private final HouseService houseService;
    @GetMapping
    public String getAllBooking(Model model) {
        model.addAttribute("bookings", bookingService.getAllBooking());
        return "booking/booking";
    }
//    @GetMapping("/{id}")
//    public String getById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("booking", bookingService.getBookingById(id));
//        return "booking/bookingPage";
//    }
    @GetMapping("/newBookings")
    public String createHouse(Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("houses", houseService.getAllHouses());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "booking/newBooking";
    }
    @PostMapping("/saveBooking")
    public String saveHouse(@ModelAttribute("newBooking") Booking booking,
                            @RequestParam("house.id") Long houseId,
                            @RequestParam("customer.id") Long customerId) {
        House house = houseService.getHouseById(houseId);
        house.setIsBooked(true);
        Customer customer = customerService.getCustomerById(customerId);
        booking.setHouse(house);
        booking.setCustomer(customer);
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }
    @GetMapping("/update/{id}")
    public String updateBooking(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking",booking);
        model.addAttribute("houses",houseService.getAllHouses());
        model.addAttribute("customers",customerService.getAllCustomers());
        return "booking/updateBooking";
    }
    @PostMapping("/saveUpdate/{id}")
        public String saveBooking(@ModelAttribute("booking") Booking booking,
                @RequestParam("houseName") Long houseId,
                @RequestParam("customerName") Long customerId,
                @PathVariable Long id){
            House house = houseService.getHouseById(houseId);
            Customer customer = customerService.getCustomerById(customerId);
            booking.setHouse(house);
            booking.setCustomer(customer);
            bookingService.updateBookingById(id,booking);
            return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/bookings";
    }
}

