package com.samplemysql.config;

import com.samplemysql.model.Customer;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<String> {

    private static final  ThreadLocal<Customer>  currentCustomer = new ThreadLocal<>();
    public static void setCurrentCustomer(Customer customer) {
        currentCustomer.set(customer);
    }


    @Override
    public Optional getCurrentAuditor() {
        Customer customer = currentCustomer.get();
        if (null != customer && !ObjectUtils.isEmpty(customer)) {
            String firstName = customer.getFirstName();
            String lastName = customer.getLastName();
            return Optional.of(firstName +" "+lastName);
        }
        return Optional.empty();
    }
}
