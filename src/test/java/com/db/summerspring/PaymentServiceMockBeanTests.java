package com.db.summerspring;

import com.db.summerspring.model.Payment;
import com.db.summerspring.repository.PaymentRepository;
import com.db.summerspring.service.IPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PaymentServiceMockBeanTests {

    @MockBean
    PaymentRepository paymentRepository;
    IPaymentService paymentService;

    @Autowired
    public PaymentServiceMockBeanTests(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

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
    }
    @Test
    void shouldFindByCustomerName () {
        assertArrayEquals(paymentRepository.findByCustomerName("user1").toArray(),
                paymentService.getAllByCustomerName("user1").toArray());
        assertNull(paymentService.getAllByCustomerName("user2"));
        assertTrue(paymentService.getAllByCustomerName("user3").isEmpty());
        assertArrayEquals(paymentRepository.findByCustomerName("user4").toArray(),
                paymentService.getAllByCustomerName("user4").toArray());

    }


    @Test
    void shouldFindAllContainingLetter(){
        assertArrayEquals(paymentRepository.getAllPaymentsWithCustomerNameContainingLetter("1").toArray(),
                paymentService.getAllContaining("1").toArray());
        //assertNull(paymentService.getAllContaining("2"));
        assertTrue(paymentService.getAllContaining("3").isEmpty());
        assertArrayEquals(paymentRepository.getAllPaymentsWithCustomerNameContainingLetter("4").toArray(),
                paymentService.getAllContaining("4").toArray());
    }

    @Test
    void shouldFindAll(){
        assertArrayEquals(paymentRepository.findAll().toArray(),paymentService.getAll().toArray());
        //assertNull(paymentService.getAll());
        assertTrue(paymentService.getAll().isEmpty());
    }

}
