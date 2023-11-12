package com.javadb.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Account;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.AccountRepository;

public class AccountService implements ServiceContract<Account, UUID> {

  private AccountRepository repo;

  public AccountService(AccountRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<Account> create(Account account) {
    return repo.add(account);
  }

  @Override
  public List<Account> getAll() {
    return repo.getAll();
  }

  @Override
  public Optional<Account> findBy(UUID id) {
    return repo.findBy(id);
  }

  public Optional<Account> findByAccountNo(Integer accountNo) {
    return repo.findByAccountNo(accountNo);
  } 

  @Override
  public Optional<Account> update(UUID id, Account account) {
    Optional<Account> accountOptional = repo.findBy(id);
    Account oldAccount = null;
    if (accountOptional.isPresent()) {
      oldAccount = accountOptional.get();
      oldAccount.setBalance(account.getBalance());
      oldAccount.setPin(account.getPin());
      return repo.add(oldAccount);
    } 
    return Optional.empty();
  }

  @Override
  public Optional<Account> delete(UUID id) {
    return repo.remove(id);
  }
  
}
