package com.db.summerspring.service;

import com.db.summerspring.model.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("development")
@Qualifier("RealNamesMockPaymentService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RealNamesMockPaymentService extends BaseMockPaymentService {
    @PostConstruct
    public void init() {
        paymentList.add(new Payment(1, 123, "description", "Gigle"));
        paymentList.add(new Payment(2, 124, "description2", "Andrei"));
        paymentList.add(new Payment(3, 124, "description3", "Cosmin"));
        paymentList.add(new Payment(4, 125, "description4", "Andreea"));
        System.out.println("RealNamesMockPaymentService");
    }
}
