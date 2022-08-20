package com.db.summerspring.service;

import com.db.summerspring.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPaymentService {
    List<Payment> getAll();

    List<Payment> getAllByCustomerName(String customerName);

    List<Payment> getAllContaining(String letter);

    Payment add(Payment p);

    Payment getById(int pid);
}
