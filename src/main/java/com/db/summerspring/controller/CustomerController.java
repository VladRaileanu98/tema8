package com.db.summerspring.controller;

import com.db.summerspring.exception.NotFoundException;
import com.db.summerspring.model.Customer;
import com.db.summerspring.repository.CustomerRepository;
import com.db.summerspring.service.IPaymentService;
import com.db.summerspring.service.ValidityChecker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/customer")
public class CustomerController {
    CustomerRepository customerRepository;
    ValidityChecker validityChecker;


    @Autowired
    public CustomerController(CustomerRepository customerRepository, ValidityChecker validityChecker) {
        this.customerRepository = customerRepository;
        this.validityChecker = validityChecker;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ModelAndView arrayIndexOutOfBoundsHandler(ArrayIndexOutOfBoundsException e) {
        ModelAndView modelAndView = new ModelAndView("error-message");
        modelAndView.addObject("errorMessage", "Array index out of bound.");
        return  modelAndView;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView nullPointerExceptionHandler(NullPointerException e) {
        ModelAndView modelAndView = new ModelAndView("error-message");
        modelAndView.addObject("errorMessage", "Null pointer exception");
        return modelAndView;
    }

    @GetMapping("/exception/nullpointer")
    public void nullPointerException() {
        throw new NullPointerException("Null pointer exception");
    }

    @GetMapping("/exception/arrayindex")
    public void arrayIndexException() {
        throw new ArrayIndexOutOfBoundsException("Array index out of bounds exception");
    }


    @ResponseBody
    @ApiOperation(value = "Find customer by username", response = Customer.class,
            notes = "Return customer based on their username", nickname = "Get by username")
    @GetMapping("/get/{username}")
    public Customer getByUsername(@PathVariable(value = "username") String username) {
        try {
            return customerRepository.getByUsername(username);
        } catch (Exception e) {
            throw new NotFoundException("Customer with username " + username + " not found.");
        }
    }

    @GetMapping("/count/{firstName}")
    public int countByFirstName(@PathVariable(value = "firstName") String firstName) {
        return customerRepository.countByFirstName(firstName);
    }

    @PutMapping("/new")
    public void addCustomer(@RequestBody Customer customer){
        customerRepository.insertCustomer(customer);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteCustomer(@PathVariable(value = "username") String username){
        customerRepository.deleteCustomer(username);
    }

    @GetMapping("/get/by-city/{city}")
    public ModelAndView getCustomersByCity(@PathVariable(value = "city") String city) {
        ModelAndView modelAndView = new ModelAndView("list-customers");
        List<Customer> listOfCustomers = customerRepository.getCustomersByCity(city);
        modelAndView.addObject("customers", listOfCustomers);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView("add-customer");
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/th-new")
    public ModelAndView addThCustomer(@ModelAttribute Customer customer) {
        if (Objects.equals(customer.getUsername(), "") || Objects.equals(customer.getFirstName(), "") ||
                Objects.equals(customer.getLastName(), "") || Objects.equals(customer.getPhone(), "")) {
            ModelAndView modelAndView = new ModelAndView("error-message");
            modelAndView.addObject("errorMessage","All new customer fields are required..." +
                    " Account wasn`t created");
            return modelAndView;
        }
        if (validityChecker.isNewCustomerValid(customer)==false) {
            ModelAndView modelAndView = new ModelAndView("error-message");
            modelAndView.addObject("errorMessage","This phone number or username is already used.");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("added-customer");
        modelAndView.addObject("customer", customer);
        customerRepository.insertCustomer(customer);
        return modelAndView;
    }



}
