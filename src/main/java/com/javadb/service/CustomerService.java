package com.javadb.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Customer;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.CustomerRepository;

public class CustomerService implements ServiceContract<Customer, UUID> {

  private CustomerRepository repo;

  public CustomerService(CustomerRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<Customer> create(Customer customer) {
    return repo.add(customer);
  }

  @Override
  public List<Customer> all() {
    return repo.getAll();
  }

  @Override
  public Optional<Customer> get(UUID id) {
    return repo.find(id);
  }

  @Override
  public Optional<Customer> update(UUID id, Customer customer) {
    return repo.add(customer);
  }

  @Override
  public Optional<Customer> delete(UUID id) {
    return repo.remove(id);
  }
  
}
