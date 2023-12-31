package com.javadb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Account;
import com.javadb.db.Database;
import com.javadb.util.Utility;

public class AccountRepository extends Database
    implements Repository<Account, UUID> {

  private ResultSet resultSet;
  private CustomerRepository customerRepo;
  private AccountTypeRepository accountTypeRepo;

  public AccountRepository() {
    this.customerRepo = new CustomerRepository();
    this.accountTypeRepo = new AccountTypeRepository();
  }

  @Override
  public Optional<Account> add(Account account) {
    String sql = "";
    int inserted = -1;

    if (!findBy(account.getId()).isEmpty()) {
      sql = "UPDATE account SET balance=?, pin=? WHERE id=?";
      inserted = postQuery(sql, account.getBalance(), account.getPin(), account.getId().toString());
    } else {
      long accountNo = 0;
      while(true) {
        if (!findByAccountNo(account.getAccountNo()).isPresent()) {
          accountNo = account.getAccountNo();
          break;
        }
        account.setAccountNo(Utility.generateAccountNo());
      }

      sql = "INSERT INTO account (id, customer_id, account_type_id, balance, pin, account_no) VALUES (?, ?, ?, ?, ?, ?)";

      inserted = postQuery(sql, account.getId().toString(), account.getCustomer().getId(), account.getAccountType().getId(), account.getBalance(), account.getPin(), accountNo);
      
    }

    if (inserted != -1) {
      return Optional.ofNullable(findBy(account.getId()).get());
    }

    return Optional.empty();
  }

  @Override
  public List<Account> getAll() {
    List<Account> list = new ArrayList<>();

    try {
      String sql = "SELECT * FROM account;";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        Account act = new Account();

        act.setId(UUID.fromString(resultSet.getObject(1).toString()));
        act.setCustomer(customerRepo.findBy(UUID.fromString(resultSet.getObject(2).toString())).get());
        act.setAccountType(accountTypeRepo.findBy(UUID.fromString(resultSet.getObject(3).toString())).get());
        act.setBalance(resultSet.getDouble(4));
        act.setPin(resultSet.getInt(5));
        act.setAccountNo(resultSet.getLong(6));
        act.setCreated(resultSet.getDate(7));
        act.setUpdated(resultSet.getDate(8));

        list.add(act);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public Optional<Account> findBy(UUID id) {
    try {
      String sql = "SELECT * FROM account WHERE account.id = ?";
      return this.fetch(sql, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public Optional<Account> findByAccountNo(Long accountNo) {
    try {
      String sql = "SELECT * FROM account WHERE account.account_no = ?";
      return this.fetch(sql, accountNo);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<Account> remove(UUID id) {
    Optional<Account> act = findBy(id);
    if (act.isPresent()) {
      try {
        String sql = "DELETE FROM account WHERE account.id = ?";
        int result = postQuery(sql, id);
        if (result != -1 && result > 0) {
          return act;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }

  private Optional<Account> fetch(String sql, Object param) throws SQLException {
    resultSet = getQuery(sql, param);

    if (resultSet.next()) {
      Account act = new Account();
      
      act.setId(UUID.fromString(resultSet.getObject(1).toString()));
      act.setCustomer(customerRepo.findBy(UUID.fromString(resultSet.getObject(2).toString())).get());
      act.setAccountType(accountTypeRepo.findBy(UUID.fromString(resultSet.getObject(3).toString())).get());
      act.setBalance(resultSet.getDouble(4));
      act.setPin(resultSet.getInt(5));
      act.setAccountNo(resultSet.getLong(6));
      act.setCreated(resultSet.getDate(7));
      act.setUpdated(resultSet.getDate(8));
      return Optional.ofNullable(act);
    }
    return Optional.empty();
  }

}
