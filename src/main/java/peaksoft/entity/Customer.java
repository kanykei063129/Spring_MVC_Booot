package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Agency> agencies;
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Booking> booking;

    public Customer(String firstName, String lastName, String email, Gender gender, String phoneNumber, LocalDate dateOfBirth, List<Agency> agencies, List<Booking> booking) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.agencies = agencies;
        this.booking = booking;
    }
}
