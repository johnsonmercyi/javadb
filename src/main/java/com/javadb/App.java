package com.javadb;

import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Account;
import com.javadb.bean.Customer;
import com.javadb.bean.User;
import com.javadb.repository.AccountRepository;
import com.javadb.repository.AccountTypeRepository;
import com.javadb.repository.CustomerRepository;
import com.javadb.repository.UserRepository;
import com.javadb.service.AccountService;
import com.javadb.service.AccountTypeService;
import com.javadb.service.CustomerService;
import com.javadb.service.UserService;

public class App {

  static void testUser() {
    UserService service = new UserService(new UserRepository());

    // Test create() method
    Customer cst = new Customer();
    cst.setFirstname("Uzondu");
    cst.setLastname("Mmuo");

    Optional<User> createdUser = service.create(new User(UUID.randomUUID(),
    cst, "uzommuo", "123456", "uzommuo@gmail.com", null, null));

    if (createdUser.isPresent()) {
      System.out.println("Created: " + createdUser.get());
    } else {
      System.out.println("Not created!");
    }

    // Test read() method
    // System.out.println(service.getAll());

    // Test update() method
    // Optional<User> userOptional =
    // service.findBy(UUID.fromString("a28ce7a2-78e3-4cbc-9d0e-fa71ac78c617"));
    // if (userOptional.isPresent()) {
    // User user = userOptional.get();
    // user.setUsername("uzomadu");
    // Optional<User> updatedUserOptional = service.update(user.getId(), user);
    // if(updatedUserOptional.isPresent())
    // System.out.println("User updated: " + updatedUserOptional.get());
    // else
    // System.out.println("User not updated!");
    // } else {
    // System.out.println("User not found!");
    // }

    // Test delete() method
    // Optional<User> checkedUserOptional = service.findBy(UUID.fromString("b7cf3ee0-43a6-4248-bff6-bc53412fc604"));
    // if (checkedUserOptional.isPresent()) {
    //   Optional<User> deletedUserOptional = service.delete(checkedUserOptional.get().getId());
    //   if (deletedUserOptional.isPresent())
    //     System.out.println("User deleted: " + deletedUserOptional.get());
    //   else
    //     System.out.println("User not deleted!");
    // } else {
    //   System.out.println("User not found!");
    // }
  }

  static void testAccountType() {
    AccountTypeService service = new AccountTypeService(new AccountTypeRepository());

    // Test create() method
    // AccountType accountType = new AccountType();
    // accountType.setType("DemoType");
    // Optional<AccountType> accountTypeOptional = service.create(accountType);
    // if (accountTypeOptional.isPresent()) {
    // System.out.println("Account type created: " + accountTypeOptional.get());
    // } else {
    // System.out.println("Account type not created!");
    // }

    // Test read() method
    // System.out.println(service.getAll());

    // Test update() method
    // Optional<AccountType> accountTypeOptional = service.findByType("Express");
    // if (accountTypeOptional.isPresent()) {
    // AccountType type = accountTypeOptional.get();
    // type.setType("Savings");
    // accountTypeOptional = service.update(type.getId(), type);
    // if (accountTypeOptional.isPresent()) {
    // System.out.println("Updated " + accountTypeOptional.get());
    // } else {
    // System.out.println("Account type not updated.");
    // }
    // } else {
    // System.out.println("Account type not found!");
    // }

    // Test delete() method
    // Optional<AccountType> accountTypeOptional = service.findByType("DemoType");
    // if (accountTypeOptional.isPresent()) {
    //   accountTypeOptional = service.delete(accountTypeOptional.get().getId());
    //   if (accountTypeOptional.isPresent()) {
    //     System.out.println("Deleted " + accountTypeOptional.get());
    //   } else {
    //     System.out.println("Account type not deleted.");
    //   }
    // } else {
    //   System.out.println("Account type not found!");
    // }
  }

  static void testAccount() {
    AccountService service = new AccountService(new AccountRepository());
    // UserService userService = new UserService(new UserRepository());
    // AccountTypeService acctTypeService = new AccountTypeService(new AccountTypeRepository());
    
    //Test create() method
    // User user = userService.findBy(UUID.fromString("a28ce7a2-78e3-4cbc-9d0e-fa71ac78c617")).get();
    // Account acct = new Account();
    // acct.setCustomer(user.getCustomer());
    // acct.setAccountType(acctTypeService.getAll().get(0));
    // acct.setBalance(0);
    // acct.setPin(1234);

    // Optional<Account> acctOptional =  service.create(acct);
    // if (acctOptional.isPresent()) {
    //   System.out.println("Created Account: " + acctOptional.get());
    // } else {
    //   System.out.println("Account not created!");
    // }

    // Test getAll() method
    // System.out.println(service.getAll());

    // Test findBy() method
    // System.out.println(service.findBy(UUID.fromString("ca813e85-e87f-4137-a874-ec0863b3c9a6")).get());

    // Test upadte() method
    // Optional<Account> acctOptional = service.findByAccountNo(2495820861L);

    // if (acctOptional.isPresent()) {
    //   Account acct = acctOptional.get();
    //   acct.setPin(9876);
    //   acctOptional = service.update(acct.getId(), acct);
    //   if (acctOptional.isPresent()) {
    //     System.out.println("Account updated: " + acctOptional.get());
    //   } else {
    //     System.out.println("Account not updated!");
    //   }
    // } else {
    //   System.out.println("Account not found!");
    // }

    // Test delete() method
    // Optional<Account> acctOptional = service.findByAccountNo(2495820861L);

    // if (acctOptional.isPresent()) {
    //   acctOptional = service.delete(acctOptional.get().getId());
    //   if (acctOptional.isPresent()) {
    //     System.out.println("Account deleted: " + acctOptional.get());
    //   } else {
    //     System.out.println("Account not deleted!");
    //   }
    // } else {
    //   System.out.println("Account not found!");
    // }

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
    // testAccountType();
    // testAccount();
  }
}
