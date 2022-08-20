package com.db.summerspring.service;

import com.db.summerspring.model.Payment;
import com.db.summerspring.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
//@Profile("production")
public class ProdPaymentService implements IPaymentService{

    PaymentRepository paymentRepository;

    @Autowired
    public ProdPaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> getAll() {
        return (List<Payment>) paymentRepository.findAll();
    }

    public List<Payment> getAllByCustomerName(String customerName) {
        return paymentRepository.findByCustomerName(customerName);
    }

    public List<Payment> getAllContaining(String letter) {
        return paymentRepository.getAllPaymentsWithCustomerNameContainingLetter(letter);
    }

    @Transactional
    public Payment add(Payment p) {
        // p - nu contine id
        Payment newPayment = paymentRepository.save(p); // insert into payment (...) values (....)
        return newPayment; // contine si id care e pus cu auto increment de db
    }

    public Payment getById(int pid) {
        Payment p = paymentRepository.findById(pid).get();
        return p;
    }
}
