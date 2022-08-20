package com.db.summerspring.controller;

import com.db.summerspring.model.Customer;
import com.db.summerspring.model.Payment;
import com.db.summerspring.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    //TODO: service + repository
//    @PersistenceContext
//    EntityManager em;


//    @Qualifier("MockPaymentService")
    IPaymentService paymentService;
    IPaymentService paymentService2;
    @Autowired
    public PaymentController(IPaymentService paymentService1, IPaymentService paymentService2) {
        this.paymentService = paymentService1;
        this.paymentService2 = paymentService2;
    }

    @GetMapping("/payments/getAllByCustomerName2")
    @ResponseBody
    public List<Payment> getByCustomerName2(@RequestParam String customerName){
        return paymentService2.getAllByCustomerName(customerName);
    }


    @GetMapping("/payments")  // /payments && /payments?customer=gigel && /payments?letter=x
    @ResponseBody
    public List<Payment> getAllPayments(@RequestParam(name = "customer", required = false) String customerName,
                                        @RequestParam(name = "letter", required = false) String letter) {
//        Query q = em.createNativeQuery("SELECT * FROM payment");
//        Query q2 = em.createQuery("SELECT p FROM Payment p");
//        return (List<Payment>)q2.getResultList();
        logger.info("Get all payments");

        if (customerName == null && letter == null) {
            logger.info("Get all");
            return paymentService.getAll();
        }

        // ce se intampla daca ambele nu sunt null ?

        if (customerName != null) {
            logger.info("Get all by customer name: " + customerName);
            return paymentService.getAllByCustomerName(customerName);
        }

        if (letter != null) {
            logger.info("Get all by letter: " + letter);
            return paymentService.getAllContaining(letter);
        }

        return null;
    }

    @PostMapping("payments")
//    @Transactional
    @ResponseBody
    public Payment addPayment(@RequestBody Payment p) {
//        em.persist(p);
        return paymentService.add(p);
    }

    @PostMapping("entity/payments") // url-ul nu e corect
    public ResponseEntity<Payment> create(@RequestBody Payment payment)
            throws URISyntaxException {
        Payment createdCustomer = paymentService.add(payment);
        if (createdCustomer == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(createdCustomer.getId()).toUri();
            return ResponseEntity.created(uri)
                    .body(createdCustomer);
        }
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getById(@PathVariable(name = "id") int pid) {
        Payment p = paymentService.getById(pid);

        ResponseCookie cookie = ResponseCookie.from("test", "example").build();

        ResponseEntity<Payment> response = ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(p);

        return response;
    }
    @ResponseBody
    @GetMapping("/payments/getAll")
    public List<Payment> getAll() {
        return paymentService.getAll();

    }
    @ResponseBody
    @GetMapping("/payments/getAllByCustomerName")
    public List<Payment> getAllByCustomerName(@RequestParam String customerName) {
        return paymentService.getAllByCustomerName(customerName);
    }
    @ResponseBody
    @GetMapping("/payments/getAllContainingLetter")
    public List<Payment> getAllContainingLetter(@RequestParam(name = "letter") String letter) {
        return paymentService.getAllContaining(letter);
    }
    @GetMapping("/exception/nullpointer")
    public void nullPointerException() {
        throw new NullPointerException("Null pointer exception");
    }

    @GetMapping("/exception/arrayindex")
    public void arrayIndexException() {
        throw new ArrayIndexOutOfBoundsException("Array index out of bounds exception");
    }
    
}
