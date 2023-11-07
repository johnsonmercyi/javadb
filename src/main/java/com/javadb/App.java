package com.javadb;

import java.util.Optional;

import com.javadb.bean.Customer;
import com.javadb.repository.CustomerRepository;
import com.javadb.service.CustomerService;

public class App {
  public static void main(String[] args) {
    // Test Customer Service methods
    CustomerService service = new CustomerService(new CustomerRepository());
    

    // Test: create() method
    // Customer cst = new Customer();
    // cst.setFirstname("Izunna");
    // cst.setLastname("Enyim");
    // cst.setAddress("New lane avenue");
    // Optional<Customer> optionalCst = service.create(cst);
    // if (!optionalCst.isEmpty()) {
    //   System.out.println("Created: " + optionalCst.get());
    // } else {
    //   System.out.println("Empty Object!");
    // }

    // Test: update() method
    Customer cstUpdate = new Customer();
    cstUpdate.setFirstname("Izunnaya");
    cstUpdate.setLastname("Enyim");
    cstUpdate.setAddress("New lane avenue");
    Optional<Customer> optionalCstUpdate = service.update(4, cstUpdate);
    if (!optionalCstUpdate.isEmpty()) {
      System.out.println("Updated: " + optionalCstUpdate.get());
    } else {
      System.out.println("Empty Object!");
    }


    // Test: all() method
    System.out.println(service.getAll());

    // Test: get() method
    // System.out.println(service.findBy(2).get());
  }
}
