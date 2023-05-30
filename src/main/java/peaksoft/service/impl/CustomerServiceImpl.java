package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.CustomerRepository;
import peaksoft.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AgencyRepository agencyRepository;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    @Override
    public Customer getCustomerById(Long id) {
            return customerRepository.findById(id)
                    .orElseThrow(() -> new NullPointerException("Customer with id: " + id + " is not found"));
    }
    @Override
    public void updateCustomerById(Long id, Customer customer) {
       Customer customer1=customerRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Customer with id: " + id + " is not found"));
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setEmail(customer.getEmail());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setDateOfBirth(customer.getDateOfBirth());
       customerRepository.save(customer1);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else throw new NullPointerException("Customer with id: " + id + " is not found");
    }
    @Override
    public void assignCustomerToAgency(Long customerId,Long agencyId) {
        Customer customer=customerRepository.findById(customerId).orElseThrow(() -> new NullPointerException("Customer with id: " + customerId + " is not found"));
        Agency agency = agencyRepository.findById(agencyId).orElseThrow(() -> new NullPointerException("Customer with id: " + agencyId + " is not found"));

//            List<Customer>customers = new ArrayList<>();
//            List<Agency> agencies = new ArrayList<>();

            customer.getAgencies().add(agency);
            agency.getCustomers().add(customer);
            customerRepository.save(customer);
            agencyRepository.save(agency);
        }
    }
