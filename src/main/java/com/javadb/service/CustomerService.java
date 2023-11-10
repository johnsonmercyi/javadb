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
  public List<Customer> getAll() {
    return repo.getAll();
  }

  @Override
  public Optional<Customer> findBy(UUID id) {
    return repo.findBy(id);
  }

  @Override
  public Optional<Customer> update(UUID id, Customer customer) {
    Customer oldCustomer = repo.findBy(id).get();
    oldCustomer.setFirstname(customer.getFirstname());
    oldCustomer.setLastname(customer.getLastname());
    oldCustomer.setAddress(customer.getAddress());
    return repo.add(oldCustomer);
  }

  @Override
  public Optional<Customer> delete(UUID id) {
    return repo.remove(id);
  }
  
}
