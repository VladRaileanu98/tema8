package com.db.summerspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="payment")
public class Payment implements Serializable { // POJO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private float amount;

    @Column(length = 500)
    private String description;

    @Column(length = 100, nullable = false)
    private String customerName;

    // constructor fara param
    // getter
    // setter


    public Payment() { // obligatoriu
    }

    public Payment(int id, float amount, String description, String customerName) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.customerName = customerName;
    }

    public Payment(float amount, String description, String customerName) {
        this.amount = amount;
        this.description = description;
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Float.compare(payment.amount, amount) == 0 && Objects.equals(description, payment.description) && Objects.equals(customerName, payment.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, customerName);
    }
}
