package com.db.summerspring.repository;

import com.db.summerspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CustomerRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Customer getByUsername(String username) {
        String statement = "SELECT * FROM customers WHERE username = ?";
        return jdbcTemplate.queryForObject(statement, new CustomerRowMapper(), username);
    }

    public int countByFirstName(String firstName) {
        String statement = "SELECT COUNT(*) FROM customers WHERE first_name = ?";
        return jdbcTemplate.queryForObject(statement,Integer.class,firstName);
    }
    public void insertCustomer(Customer customer) {
        String statement = "INSERT INTO customers (username,last_name,first_name,phone," +
                "address,city,postal_code,country) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(statement,customer.getUsername(),
                customer.getLastName(),customer.getFirstName(),
                customer.getPhone(),customer.getAddress(),customer.getCity(),
                customer.getPostalCode(),customer.getCountry());
    }

    public void deleteCustomer(String username){
        String statement = "DELETE FROM customers WHERE username = ?";
        jdbcTemplate.update(statement, username);
    }

    public List<Customer> getCustomersByCity (String city) {
        String statement = "SELECT * FROM customers WHERE city = ?";
        return jdbcTemplate.query(statement, new CustomerRowMapper(), city);
    }

    public int countByUsernameOrPhone(String username,String phone) {
        String statement = "SELECT COUNT(*) FROM customers WHERE username = ? OR phone = ?";
        return jdbcTemplate.queryForObject(statement, Integer.class, username, phone);
    }
}
