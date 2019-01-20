package com.limbo.example.user.service;

import com.limbo.example.user.model.Customer;
import com.limbo.example.user.repository.CustomerRepository;
import org.bouncycastle.asn1.x500.style.RFC4519Style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createUser(Customer user){
        return customerRepository.save(user);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public  Customer getCustomer(String name){
        Customer customer = new Customer();
        customer.setUserName(name);
        Example<Customer> param = Example.of(customer);
        return customerRepository.findOne(param).get();
    }
}
