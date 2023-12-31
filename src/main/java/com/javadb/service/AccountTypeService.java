package com.javadb.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.AccountType;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.AccountTypeRepository;

public class AccountTypeService implements ServiceContract<AccountType, UUID> {

  private AccountTypeRepository repo;

  public AccountTypeService(AccountTypeRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<AccountType> create(AccountType accountType) {
    return repo.add(accountType);
  }

  @Override
  public List<AccountType> getAll() {
    return repo.getAll();
  }

  @Override
  public Optional<AccountType> findBy(UUID id) {
    return repo.findBy(id);
  }

  public Optional<AccountType> findByType(String type) {
    return repo.findByType(type);
  } 

  @Override
  public Optional<AccountType> update(UUID id, AccountType accountType) {
    Optional<AccountType> oldAccountTypeOptional = repo.findBy(id);
    AccountType oldAccountType = null;
    if (oldAccountTypeOptional.isPresent()) {
      oldAccountType = oldAccountTypeOptional.get();
      oldAccountType.setType(accountType.getType());
      return repo.add(oldAccountType);
    }
    return Optional.empty();
  }

  @Override
  public Optional<AccountType> delete(UUID id) {
    return repo.remove(id);
  }

}
