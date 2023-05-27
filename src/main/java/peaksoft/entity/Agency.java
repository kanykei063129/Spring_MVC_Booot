package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Country;

import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agency_gen")
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Country country;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String imageLink;
    @ManyToMany(mappedBy = "agencies", cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private List<Customer> customers;
    @OneToMany(mappedBy = "agency", cascade = {CascadeType.MERGE, CascadeType.DETACH, REMOVE})
    private List<House> houses;

    public Agency(String name, Country country, String phoneNumber, String email, String imageLink, List<Customer> customers, List<House> houses) {
        this.name = name;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageLink = imageLink;
        this.customers = customers;
        this.houses = houses;
    }
}
