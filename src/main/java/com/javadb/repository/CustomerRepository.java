package com.javadb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javadb.bean.Customer;
import com.javadb.db.Database;

public class CustomerRepository extends Database implements Repository<Customer, Integer> {

  private ResultSet resultSet;

  @Override
  public Optional<Customer> add(Customer customer) {
    String sql = "";
    int inserted = -1;

    if (!findBy(customer.getId()).isEmpty()) {
      sql = "UPDATE customer SET firstname=?, lastname=?, address=? WHERE id=?";
      inserted = postQuery(sql, customer.getFirstname(), customer.getLastname(), customer.getAddress(), customer.getId());
    } else {
      sql = "INSERT INTO customer (firstname, lastname, address) VALUES (?, ?, ?)";
      inserted = postQuery(sql, customer.getFirstname(), customer.getLastname(), customer.getAddress());
    }

    if (inserted != -1) {
      return Optional.ofNullable(customer);
    }

    return Optional.empty();
  }

  @Override
  public List<Customer> getAll() {
    Customer cst = null;
    List<Customer> list = new ArrayList<>();

    try {
      String sql = "SELECT * FROM customer;";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        cst = new Customer(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getDate(5), 
            resultSet.getDate(6)
        );
        list.add(cst);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    closeConnection();
    return list;
  }

  @Override
  public Optional<Customer> findBy(Integer id) {
    try {
      String sql = "SELECT * FROM customer WHERE customer.id = ?";
      resultSet = getQuery(sql, id);
      if (resultSet.next()) {
        return Optional.ofNullable(new Customer(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getDate(5),
            resultSet.getDate(6)
        ));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<Customer> remove(Integer id) {
    return null;
  }
  
}
