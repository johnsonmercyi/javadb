package com.javadb.service;

import java.util.List;
import java.util.Optional;

import com.javadb.bean.Customer;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.CustomerRepository;

public class CustomerService implements ServiceContract<Customer, Integer> {

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
  public Optional<Customer> findBy(Integer id) {
    return repo.findBy(id);
  }

  @Override
  public Optional<Customer> update(Integer id, Customer customer) {
    Customer oldCustomer = repo.findBy(id).get();
    oldCustomer.setFirstname(customer.getFirstname());
    oldCustomer.setLastname(customer.getLastname());
    oldCustomer.setAddress(customer.getAddress());
    return repo.add(oldCustomer);
  }

  @Override
  public Optional<Customer> delete(Integer id) {
    return repo.remove(id);
  }
  
}
