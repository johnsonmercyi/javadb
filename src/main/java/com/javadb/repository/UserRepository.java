package com.javadb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Account;
import com.javadb.bean.Customer;
import com.javadb.bean.User;
import com.javadb.db.Database;

public class UserRepository extends Database implements Repository<User, UUID> {

  private CustomerRepository customerRepo;
  private AccountTypeRepository acctTypeRepo;
  private AccountRepository accountRepo;
  private ResultSet resultSet;

  public UserRepository() {
    customerRepo = new CustomerRepository();
    acctTypeRepo = new AccountTypeRepository();
    accountRepo = new AccountRepository();
  }

  @Override
  public Optional<User> add(User user) {
    String sql = "";
    int inserted = -1;

    if (findBy(user.getId()).isPresent()) {
      sql = "UPDATE user SET username=?, password=?, email=? WHERE id=?";
      inserted = postQuery(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    } else {
      if (findByName(user.getUsername()).isPresent()) {
        System.out.println("Username has been taken.");
      } else if (findByEmail(user.getEmail()).isPresent()) {
        System.out.println("This email already exists. Please try logging in if you're a registered user.");
      } else {

        // Register the Customer
        Optional<Customer> cstOptional = customerRepo.add(user.getCustomer());

        if (cstOptional != null && cstOptional.isPresent()) {

          /**
           * Create account
           */
          Account acct = new Account();
          acct.setCustomer(cstOptional.get());
          acct.setAccountType(acctTypeRepo.getAll().get(0));
          acct.setBalance(0);
          acct.setPin(1234);

          // Register user Bank Account
          Optional<Account> accountOptional = accountRepo.add(acct);
  
          if (accountOptional != null && accountOptional.isPresent()) {
            // Make the customer a User
            sql = "INSERT INTO user (id, customer_id, username, password, email) VALUES (?, ?, ?, ?, ?)";
            inserted = postQuery(sql, user.getId(), user.getCustomer().getId(), user.getUsername(), user.getPassword(), user.getEmail());            
          }
        }
      }
    }

    if (inserted != -1) {
      return Optional.ofNullable(findBy(user.getId()).get());
    }
    
    return Optional.empty();
  }

  @Override
  public List<User> getAll() {
    List<User> list = new ArrayList<>();

    try {
      String sql = "SELECT * FROM user;";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        User user = new User();

        user.setId(UUID.fromString(resultSet.getObject(1).toString()));
        user.setCustomer(customerRepo.findBy(UUID.fromString(resultSet.getString(2))).get());
        user.setUsername(resultSet.getString(3));
        user.setPassword(resultSet.getString(4));
        user.setEmail(resultSet.getString(5));
        user.setCreated(resultSet.getDate(6));
        user.setUpdated(resultSet.getDate(7));

        list.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    closeConnection();
    return list;
  }

  @Override
  public Optional<User> findBy(UUID id) {
    try {
      String sql = "SELECT * FROM user WHERE user.id = ?";
      return this.fetch(sql, id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  //find by name
  public Optional<User> findByName(String name) {
    try {
      String sql = "SELECT * FROM user WHERE user.username = ?";
      return this.fetch(sql, name);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  //find by email
  public Optional<User> findByEmail(String email) {
    try {
      String sql = "SELECT * FROM user WHERE user.email = ?";
      return this.fetch(sql, email);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> remove(UUID id) {
    Optional<User> user = findBy(id);
    if (user.isPresent()) {
      try {
        String sql = "DELETE FROM user WHERE user.id = ?";
        int result = postQuery(sql, id);
        if (result != -1 && result > 0) {
          return user;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }

  private Optional<User> fetch(String sql, Object value) throws SQLException {
    resultSet = getQuery(sql, value.toString());
    if (resultSet.next()) {
      User user = new User();

      user.setId(UUID.fromString(resultSet.getObject(1).toString()));
      user.setCustomer(customerRepo.findBy(UUID.fromString(resultSet.getString(2))).get());
      user.setUsername(resultSet.getString(3));
      user.setPassword(resultSet.getString(4));
      user.setEmail(resultSet.getString(5));
      user.setCreated(resultSet.getDate(6));
      user.setUpdated(resultSet.getDate(7));

      return Optional.ofNullable(user);
    }
    return Optional.empty();
  }

}
