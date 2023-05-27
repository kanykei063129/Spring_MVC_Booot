package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_gen")
    @SequenceGenerator(name = "booking_gen", sequenceName = "booking_seq", allocationSize = 1)
    private Long id;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
//    @Column(name = "house_id")
    private House house;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
//    @Column(name = "customer_id")
    private Customer customer;

    public Booking(House houseId, Customer customerId) {
        this.house = houseId;
        this.customer = customerId;
    }
}
