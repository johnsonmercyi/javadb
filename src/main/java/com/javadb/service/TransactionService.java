package com.javadb.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Transaction;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.TransactionRepository;

public class TransactionService implements ServiceContract<Transaction, UUID> {

  private TransactionRepository transRepo;

  public TransactionService() {
    this.transRepo = new TransactionRepository();
  }

  @Override
  public Optional<Transaction> create(Transaction transaction) {
    return transRepo.add(transaction);
  }

  @Override
  public List<Transaction> getAll() {
    return transRepo.getAll();
  }

  @Override
  public Optional<Transaction> findBy(UUID id) {
    return transRepo.findBy(id);
  }

  @Override
  public Optional<Transaction> update(UUID id, Transaction value) {
    return Optional.empty();
  }

  @Override
  public Optional<Transaction> delete(UUID id) {
    return Optional.empty();
  }
  
}
