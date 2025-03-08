package com.samplemysql.service;

import com.samplemysql.model.Customer;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);

    public Customer getCustomerById(Long id);

    public Customer updateCustomer(Long id, Customer customer);

    public void deleteCustomer(Long id);


}
