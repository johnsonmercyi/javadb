package com.javadb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Account;
import com.javadb.bean.Transaction;
import com.javadb.db.Database;

public class TransactionRepository extends Database implements Repository<Transaction, UUID> {

  private ResultSet resultSet;
  private AccountRepository accountRepo;

  public TransactionRepository () {
    accountRepo = new AccountRepository();
  }

  @Override
  public Optional<Transaction> add(Transaction transaction) {
    String sql = "";
    int inserted = -1;

    sql = "INSERT INTO transaction (id, account_id, type, type_description, beneficiary, amount, description) VALUES (?, ?, ?, ?, ?, ?, ?)";

    inserted = postQuery(sql, transaction.getId().toString(),
    transaction.getAccount().getId().toString(), transaction.getType(), transaction.getTypeDescription(), transaction.getBeneficiary(), transaction.getAmount(), transaction.getDescription());

    if (inserted != -1 && inserted > 0) {
      return Optional.ofNullable(findBy(transaction.getId()).get());
    }

    return Optional.empty();
  }

  @Override
  public List<Transaction> getAll() {
    List<Transaction> list = new ArrayList<>();

    try {
      String sql = "SELECT * FROM transaction;";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        Transaction trans = new Transaction();

        trans.setId(UUID.fromString(resultSet.getObject(1).toString()));
        trans.setAccount(accountRepo.findBy(UUID.fromString(resultSet.getObject(2).toString())).get());
        trans.setType(resultSet.getString(3));
        trans.setTypeDescription(resultSet.getString(4));
        trans.setBeneficiary(resultSet.getString(5));
        trans.setAmount(resultSet.getDouble(6));
        trans.setDescription(resultSet.getString(7));
        trans.setCreated(resultSet.getDate(8));
        trans.setUpdated(resultSet.getDate(9));

        list.add(trans);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<Transaction> findByAccountNo(Long accountNo) {
    List<Transaction> list = new ArrayList<>();
    try {
      Optional<Account> acctOptional = accountRepo.findByAccountNo(accountNo);
      if (acctOptional.isPresent()) {
        UUID id = acctOptional.get().getId();

        String sql = "SELECT * FROM transaction WHERE account_id = ?";
        resultSet = getQuery(sql);

        while (resultSet.next()) {
          Transaction trans = new Transaction();

          trans.setId(UUID.fromString(resultSet.getObject(1).toString()));
          trans.setAccount(accountRepo.findBy(UUID.fromString(resultSet.getObject(2).toString())).get());
          trans.setType(resultSet.getString(3));
          trans.setTypeDescription(resultSet.getString(4));
          trans.setBeneficiary(resultSet.getString(5));
          trans.setAmount(resultSet.getDouble(6));
          trans.setDescription(resultSet.getString(7));
          trans.setCreated(resultSet.getDate(8));
          trans.setUpdated(resultSet.getDate(9));

          list.add(trans);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public Optional<Transaction> findBy(UUID id) {
    try {
      String sql = "SELECT * FROM transaction WHERE transaction.id = ?";
      resultSet = getQuery(sql, id);

      if (resultSet.next()) {
        Transaction trans = new Transaction();

        trans.setId(UUID.fromString(resultSet.getObject(1).toString()));
        trans.setAccount(accountRepo.findBy(UUID.fromString(resultSet.getObject(2).toString())).get());
        trans.setType(resultSet.getString(3));
        trans.setTypeDescription(resultSet.getString(4));
        trans.setBeneficiary(resultSet.getString(5));
        trans.setAmount(resultSet.getDouble(6));
        trans.setDescription(resultSet.getString(7));
        trans.setCreated(resultSet.getDate(8));
        trans.setUpdated(resultSet.getDate(9));

        return Optional.ofNullable(trans);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<Transaction> remove(UUID id) {
    Optional<Transaction> trans = findBy(id);
    if (trans.isPresent()) {
      try {
        String sql = "DELETE FROM transaction WHERE id = ?";
        int result = postQuery(sql, id);

        if (result != -1 && result > 0) {
          return trans;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }
  
}
