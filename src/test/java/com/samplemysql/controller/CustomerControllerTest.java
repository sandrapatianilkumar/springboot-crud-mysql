package com.samplemysql.controller;

import com.samplemysql.model.Address;
import com.samplemysql.model.Customer;
import com.samplemysql.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testSaveCustomer() throws Exception {
        Customer customer = new Customer();
        Address address = new Address();
        address.setStreet("123 Main Street");
        address.setZipCode("12345");
        address.setCity("Anytown");
        address.setState("CA");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Customer.Gender.MALE);
        customer.setAddress(address);

        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"gender\":\"MALE\", \"address\":{\"street\":\"123 Main Street\", \"zipCode\":\"12345\", \"city\":\"Anytown\", \"state\":\"CA\"}}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andExpect(jsonPath("$.address.street").value("123 Main Street"))
                .andExpect(jsonPath("$.address.zipCode").value("12345"))
                .andExpect(jsonPath("$.address.city").value("Anytown"))
                .andExpect(jsonPath("$.address.state").value("CA"));

        verify(customerService).saveCustomer(any(Customer.class));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        Address address = new Address();
        address.setStreet("123 Main Street");
        address.setZipCode("12345");
        address.setCity("Anytown");
        address.setState("CA");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Customer.Gender.MALE);
        customer.setAddress(address);

        when(customerService.updateCustomer(anyLong(), any(Customer.class))).thenReturn(customer);

        mockMvc.perform(put("/customer/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"gender\":\"MALE\", \"address\":{\"street\":\"123 Main Street\", \"zipCode\":\"12345\", \"city\":\"Anytown\", \"state\":\"CA\"}}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andExpect(jsonPath("$.address.street").value("123 Main Street"))
                .andExpect(jsonPath("$.address.zipCode").value("12345"))
                .andExpect(jsonPath("$.address.city").value("Anytown"))
                .andExpect(jsonPath("$.address.state").value("CA"));

        verify(customerService).updateCustomer(anyLong(), any(Customer.class));
    }

    @Test
    void testGetCustomer() throws Exception {
        Customer customer = new Customer();
        Address address = new Address();
        address.setStreet("123 Main Street");
        address.setZipCode("12345");
        address.setCity("Anytown");
        address.setState("CA");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Customer.Gender.MALE);
        customer.setAddress(address);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/customer/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andExpect(jsonPath("$.address.street").value("123 Main Street"))
                .andExpect(jsonPath("$.address.zipCode").value("12345"))
                .andExpect(jsonPath("$.address.city").value("Anytown"))
                .andExpect(jsonPath("$.address.state").value("CA"));

        verify(customerService).getCustomerById(anyLong());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customer/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(anyLong());
    }
}