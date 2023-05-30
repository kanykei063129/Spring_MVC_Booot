package peaksoft.service;

import peaksoft.entity.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    void updateCustomerById(Long id, Customer customer);

    void deleteCustomerById(Long id);
    void assignCustomerToAgency( Long customerId,Long agencyId);
}