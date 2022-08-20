package com.db.summerspring.service;

import com.db.summerspring.model.Customer;
import com.db.summerspring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidityChecker {
    CustomerRepository customerRepository;

    @Autowired
    public ValidityChecker(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean isNewCustomerValid(Customer customer) {
        if (customer.getUsername() == null) {
            return false;
        }

        if (customer.getPhone() == null) {
            return false;
        }

        return customerRepository.countByUsernameOrPhone(customer.getUsername(), customer.getPhone()) == 0;
    }
}
