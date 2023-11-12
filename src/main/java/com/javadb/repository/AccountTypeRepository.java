package com.javadb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.AccountType;
import com.javadb.db.Database;

public class AccountTypeRepository extends Database implements Repository<AccountType, UUID> {

  private ResultSet resultSet;

  @Override
  public Optional<AccountType> add(AccountType accountType) {
    String sql = "";
    int inserted = -1;
    AccountType storedAccount = null;

    if (findBy(accountType.getId()).isPresent()) {
      sql = "UPDATE account_type SET type=?";
      inserted = postQuery(sql, accountType.getType());
      storedAccount = findBy(accountType.getId()).get();
    } else {
      sql = "INSERT INTO account_type (id, typ) VALUES (?, ?)";
      inserted = postQuery(sql, accountType.getId(), accountType.getType());

      storedAccount = findBy(accountType.getId()).get();
    }

    if (inserted != -1) {
      return Optional.ofNullable(storedAccount);
    }

    return Optional.empty();
  }

  @Override
  public List<AccountType> getAll() {
    List<AccountType> list = new ArrayList<>();

    try {
      String sql = "SELECT * FROM account_type;";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        AccountType accountType = new AccountType();

        accountType.setId(UUID.fromString(resultSet.getObject(1).toString()));
        accountType.setType(resultSet.getString(2));
        accountType.setCreated(resultSet.getDate(3));
        accountType.setUpdated(resultSet.getDate(4));

        list.add(accountType);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    closeConnection();
    return list;
  }

  @Override
  public Optional<AccountType> findBy(UUID id) {
    try {
      String sql = "SELECT * FROM account_type WHERE account_type.id = ?";
      return this.fetch(sql, id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<AccountType> remove(UUID id) {
    Optional<AccountType> accountType = findBy(id);
    if (accountType.isPresent()) {
      try {
        String sql = "DELETE FROM account_type WHERE account_type.id = ?";
        int result = postQuery(sql, id);
        if (result != -1 && result > 0) {
          return accountType;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }

  private Optional<AccountType> fetch(String sql, Object value) throws SQLException {
    resultSet = getQuery(sql, value.toString());
    if (resultSet.next()) {
      AccountType accountType = new AccountType();

      accountType.setId(UUID.fromString(resultSet.getObject(1).toString()));
      accountType.setType(resultSet.getString(2));
      accountType.setCreated(resultSet.getDate(3));
      accountType.setUpdated(resultSet.getDate(4));

      return Optional.ofNullable(accountType);
    }
    return Optional.empty();
  }

}
