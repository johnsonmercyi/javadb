package com.javadb;

import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Customer;
import com.javadb.bean.User;
import com.javadb.repository.CustomerRepository;
import com.javadb.repository.UserRepository;
import com.javadb.service.CustomerService;
import com.javadb.service.UserService;

public class App {

  static void testUser() {
    UserService service = new UserService(new UserRepository());

    // Test create() method
    // Customer cst = new Customer();
    // cst.setFirstname("Uzondu");
    // cst.setLastname("Mmuo");
    // Optional<User> createdUser = service.create(new User(UUID.randomUUID(), 
    //     cst, "uzommuo", "123456", "uzommuo@gmail.com", null, null));

    // if (createdUser.isPresent()) {
    //   System.out.println("Created: " + createdUser.get());
    // } else {
    //   System.out.println("Not created!");
    // }

    // Test read() method
    // System.out.println(service.getAll());

    // Test update() method
    // Optional<User> userOptional = service.findBy(UUID.fromString("a28ce7a2-78e3-4cbc-9d0e-fa71ac78c617"));
    // if (userOptional.isPresent()) {
    //   User user = userOptional.get();
    //   user.setUsername("uzomadu");
    //   Optional<User> updatedUserOptional = service.update(user.getId(), user);
    //   if(updatedUserOptional.isPresent())
    //     System.out.println("User updated: " + updatedUserOptional.get());
    //   else
    //     System.out.println("User not updated!");
    // } else {
    //   System.out.println("User not found!");
    // }

    // Test delete() method
    Optional<User> checkedUserOptional = service.findBy(UUID.fromString("b7cf3ee0-43a6-4248-bff6-bc53412fc604"));
    if (checkedUserOptional.isPresent()) {
      Optional<User> deletedUserOptional = service.delete(checkedUserOptional.get().getId());
      if (deletedUserOptional.isPresent())
        System.out.println("User deleted: " + deletedUserOptional.get());
      else
        System.out.println("User not deleted!");
    } else {
      System.out.println("User not found!");
    }
  }

  static void testCustomer() {
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
    // System.out.println("Created: " + optionalCst.get());
    // } else {
    // System.out.println("Empty Object!");
    // }

    // Test: update() method
    // Customer cstUpdate = new Customer();
    // cstUpdate.setFirstname("Izunnaya");
    // cstUpdate.setLastname("Enyim");
    // cstUpdate.setAddress("New lane avenue");
    // Optional<Customer> optionalCstUpdate =
    // service.update(UUID.fromString("4e3bd45e-3a8c-45ee-9eb6-b5d16e5ac0c0"),
    // cstUpdate);
    // if (!optionalCstUpdate.isEmpty()) {
    // System.out.println("Updated: " + optionalCstUpdate.get());
    // } else {
    // System.out.println("Empty Object!");
    // }

    // Optional<Customer> optionalCstDelete =
    // service.delete(UUID.fromString("ad74ce39-7f71-11ee-bb28-c85acfb4f4e5"));
    // if (!optionalCstDelete.isEmpty()) {
    // System.out.println("Deleted: " + optionalCstDelete.get());
    // } else {
    // System.out.println("Empty Object!");
    // }

    // Test: all() method
    System.out.println(service.getAll());

    // Test: get() method
    // System.out.println(service.findBy(UUID.fromString("e8854cc7-2c5e-41e3-8041-7979f52ea837")).get());
  }
  public static void main(String[] args) {
    testUser();
  }
}
