package com.db.summerspring.service;

import com.db.summerspring.model.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseMockPaymentService implements IPaymentService {
    protected List<Payment> paymentList = new ArrayList<>();

    @Override
    public List<Payment> getAll() {
        return paymentList;
    }

    @Override
    public List<Payment> getAllByCustomerName(String customerName) {
        return paymentList.stream()
                .filter((payment) -> payment.getCustomerName().equals(customerName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> getAllContaining(String letter) {
        return paymentList.stream()
                .filter(payment -> payment.getCustomerName().contains(letter))
                .collect(Collectors.toList());
    }

    @Override
    public Payment add(Payment payment) {
        paymentList.add(payment);
        return payment;
    }

    @Override
    public Payment getById(int paymentId) {
        return paymentList.stream()
                .filter(payment -> payment.getId() == paymentId)
                .findFirst()
                .orElse(null);
    }
}
