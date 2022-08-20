package com.db.summerspring.repository;

import com.db.summerspring.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    public List<Payment> findByCustomerName(String cname); // select * from payment where customer_name = ?

    // payment care are nume de customer care contine a
    @Query(value = "SELECT * FROM payment WHERE customer_name LIKE %?1%", nativeQuery = true)
    public List<Payment> getAllPaymentsWithCustomerNameContainingLetter(String letter);
}
