package com.samplemysql.repository;

import com.samplemysql.model.Address;
import com.samplemysql.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        Address address = new Address();
        address.setStreet("123 Main Street");
        address.setZipCode("12345");
        address.setCity("Anytown");
        address.setState("CA");

        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Customer.Gender.MALE);
        customer.setAddress(address);
    }

    @Test
    void testSaveCustomer() {
        Customer savedCustomer = customerRepository.save(customer);
        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());
        assertEquals("John", savedCustomer.getFirstName());
        assertEquals("Doe", savedCustomer.getLastName());
    }

    @Test
    void testFindCustomerById() {
        Customer savedCustomer = customerRepository.save(customer);
        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());
        assertTrue(foundCustomer.isPresent());
        assertEquals("John", foundCustomer.get().getFirstName());
        assertEquals("Doe", foundCustomer.get().getLastName());
    }

    @Test
    void testDeleteCustomer() {
        Customer savedCustomer = customerRepository.save(customer);
        customerRepository.deleteById(savedCustomer.getId());
        Optional<Customer> deletedCustomer = customerRepository.findById(savedCustomer.getId());
        assertFalse(deletedCustomer.isPresent());
    }
}