package com.db.summerspring;

import com.db.summerspring.model.Customer;
import com.db.summerspring.service.ValidityChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SummerspringApplicationTests {
	ValidityChecker validityChecker;

	@Autowired
	SummerspringApplicationTests(ValidityChecker validityChecker) {
		this.validityChecker = validityChecker;
	}

	@Test
	public void customerShouldNotBeValid() {
		Customer customer1 = new Customer("customer1","a","b","0728282","sfsdf",
				"oras","020202","Ro");
		assertFalse(validityChecker.isNewCustomerValid(customer1), "Customer " + customer1 + " was evaluated as valid, but should " +
				"be invalid");
		Customer customer2 = new Customer("customer20","a","sss","723719283",null,
				null,null,null);
		assertFalse(validityChecker.isNewCustomerValid(customer2),"Customer " + customer2 + " was evaluated as valid, but should " +
				"be invalid");
		Customer customer3 = new Customer(null, "ss", "ff", "123131343", "add",
				"ccc", "0293232", "aaa");
		assertFalse(validityChecker.isNewCustomerValid(customer3), "Customer " + customer3 + " was evaluated as valid, but should " +
				"be invalid");
		Customer customer4 = new Customer(null, "ss", "ff", null, "add",
				"ccc", "0293232", "aaa");
		assertFalse(validityChecker.isNewCustomerValid(customer4), "Customer " + customer4 + " was evaluated as valid, but should " +
				"be invalid");
	}
}