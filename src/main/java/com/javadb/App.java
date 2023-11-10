package com.javadb;

import java.util.UUID;

import com.javadb.repository.CustomerRepository;
import com.javadb.service.CustomerService;

public class App {
  public static void main(String[] args) {
    // Test Customer Service methods
    CustomerService service = new CustomerService(new CustomerRepository());
    

    // Test: create() method
    // Customer cst = new Customer();
    // System.out.println("UUID: " + cst.getId());
    // cst.setFirstname("Uzoma");
    // cst.setLastname("Deziana");
    // cst.setAddress("13 Pullup Street");
    // Optional<Customer> optionalCst = service.create(cst);
    // if (!optionalCst.isEmpty()) {
    //   System.out.println("Created: " + optionalCst.get());
    // } else {
    //   System.out.println("Empty Object!");
    // }

    // Test: update() method
    // Customer cstUpdate = new Customer();
    // cstUpdate.setFirstname("Izunnaya");
    // cstUpdate.setLastname("Enyim");
    // cstUpdate.setAddress("New lane avenue");
    // Optional<Customer> optionalCstUpdate = service.update(UUID.fromString("4e3bd45e-3a8c-45ee-9eb6-b5d16e5ac0c0"), cstUpdate);
    // if (!optionalCstUpdate.isEmpty()) {
    //   System.out.println("Updated: " + optionalCstUpdate.get());
    // } else {
    //   System.out.println("Empty Object!");
    // }

    // Optional<Customer> optionalCstDelete = service.delete(UUID.fromString("ad74ce39-7f71-11ee-bb28-c85acfb4f4e5"));
    // if (!optionalCstDelete.isEmpty()) {
    //   System.out.println("Deleted: " + optionalCstDelete.get());
    // } else {
    //   System.out.println("Empty Object!");
    // }

    // Test: all() method
    System.out.println(service.getAll());

    // Test: get() method
    // System.out.println(service.findBy(UUID.fromString("e8854cc7-2c5e-41e3-8041-7979f52ea837")).get());
  }
}
