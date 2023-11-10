package com.javadb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Customer;
import com.javadb.db.Database;

public class CustomerRepository extends Database implements Repository<Customer, UUID> {

  private ResultSet resultSet;

  @Override
  public Optional<Customer> add(Customer customer) {
    String sql = "";
    int inserted = -1;
    Customer storedCustomer = null;

    if (!findBy(customer.getId()).isEmpty()) {
      sql = "UPDATE customer SET firstname=?, lastname=?, address=? WHERE id=?";
      inserted = postQuery(sql, customer.getFirstname(), customer.getLastname(), customer.getAddress(), customer.getId().toString());
      storedCustomer = customer;
    } else {
      sql = "INSERT INTO customer (id, firstname, lastname, address) VALUES (?, ?, ?, ?)";
      inserted = postQuery(sql, customer.getId().toString(), customer.getFirstname(), customer.getLastname(), customer.getAddress());

      storedCustomer = findBy(customer.getId()).get();
    }

    if (inserted != -1) {
      return Optional.ofNullable(storedCustomer);
    }

    return Optional.empty();
  }

  @Override
  public List<Customer> getAll() {
    List<Customer> list = new ArrayList<>();

    try {
      String sql = "SELECT * FROM customer;";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        Customer cst = new Customer();

        cst.setId(UUID.fromString(resultSet.getObject(1).toString()));
        cst.setFirstname(resultSet.getString(2));
        cst.setLastname(resultSet.getString(3));
        cst.setAddress(resultSet.getString(4));
        cst.setCreated(resultSet.getDate(5));
        cst.setUpdated(resultSet.getDate(6));

        list.add(cst);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    closeConnection();
    return list;
  }

  @Override
  public Optional<Customer> findBy(UUID id) {
    try {
      String sql = "SELECT * FROM customer WHERE customer.id = ?";
      resultSet = getQuery(sql, id.toString());
      if (resultSet.next()) {
        Customer cst = new Customer();

        cst.setId(UUID.fromString(resultSet.getObject(1).toString()));
        cst.setFirstname(resultSet.getString(2));
        cst.setLastname(resultSet.getString(3));
        cst.setAddress(resultSet.getString(4));
        cst.setCreated(resultSet.getDate(5));
        cst.setUpdated(resultSet.getDate(6));

        return Optional.ofNullable(cst);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<Customer> remove(UUID id) {
    Optional<Customer> cst = findBy(id);
    if (cst.isPresent()) {
      try {
        String sql = "DELETE FROM customer WHERE customer.id = ?";
        int result = postQuery(sql, id);
        if (result != -1 && result > 0) {
          return cst;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }

}
