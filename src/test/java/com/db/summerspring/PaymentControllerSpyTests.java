package com.db.summerspring;

import com.db.summerspring.controller.CustomerController;
import com.db.summerspring.controller.PaymentController;
import com.db.summerspring.model.Payment;
import com.db.summerspring.service.IPaymentService;
import com.db.summerspring.service.MockPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PaymentControllerSpyTests {

    PaymentController paymentController;
    @Spy
    IPaymentService paymentService = new MockPaymentService();

    @BeforeEach
    public void init() {
        this.paymentController = new PaymentController(this.paymentService, this.paymentService);
    }

    @Test
    void shouldCreatePayment() {
        Payment payment = new Payment(120, "description", "customer name");
        try {
            paymentController.create(payment);
        } catch (Exception e) {
            fail("Exception throw by payment controller. " + e.getMessage());
        }
        Mockito.verify(paymentService, Mockito.times(1)).add(payment);
    }
}
