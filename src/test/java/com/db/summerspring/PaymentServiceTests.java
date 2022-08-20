package com.db.summerspring;

import com.db.summerspring.model.Customer;
import com.db.summerspring.model.Payment;
import com.db.summerspring.repository.PaymentRepository;
import com.db.summerspring.service.IPaymentService;
import com.db.summerspring.service.ProdPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {

    @Mock
    PaymentRepository paymentRepository;
    IPaymentService paymentService;

    @BeforeEach
    void init() {
        Payment payment = new Payment(100,"description","user1");
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        when(paymentRepository.findByCustomerName("user1")).thenReturn(paymentList);

        when(paymentRepository.findByCustomerName("user2")).thenReturn(null);

        when(paymentRepository.findByCustomerName("user3")).thenReturn(new ArrayList<>());

        Payment payment2 = new Payment(222,"description2","user4");
        Payment payment3 = new Payment(222,null,"user4");
        Payment payment4 = new Payment(-10,"description3","user4");
        List<Payment> paymentList2 = new ArrayList<>();
        paymentList2.add(payment2);
        paymentList2.add(payment3);
        paymentList2.add(payment4);
        when(paymentRepository.findByCustomerName("user4")).thenReturn(paymentList2);

        paymentService = new ProdPaymentService(paymentRepository);



    }

    @Test
    public void shouldFindByCustomerName() {
        Payment payment = new Payment(100,"description","user1");
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        assertArrayEquals(paymentList.toArray(),paymentService.getAllByCustomerName("user1").toArray(),"Payment list for user1 does not contain the correct payment");
        assertNull(paymentService.getAllByCustomerName("user2"),"Payment list for user2 is not null");
        assertTrue(paymentService.getAllByCustomerName("user3").isEmpty(),"Payment list for user3 is not empty");

        Payment payment2 = new Payment(222,"description2","user4");
        Payment payment3 = new Payment(222,null,"user4");
        Payment payment4 = new Payment(-10,"description3","user4");
        List<Payment> paymentList2 = new ArrayList<>();
        paymentList2.add(payment2);
        paymentList2.add(payment3);
        paymentList2.add(payment4);
        assertArrayEquals(paymentList2.toArray(),paymentService.getAllByCustomerName("user4").toArray(),"Payment list for user4 does not contain the correct payments");
    }

}
