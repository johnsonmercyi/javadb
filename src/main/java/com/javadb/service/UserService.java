package com.javadb.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.Customer;
import com.javadb.bean.User;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.UserRepository;

public class UserService implements ServiceContract<User, UUID>{

  private UserRepository repo;
  private CustomerService customerService;

  public UserService(UserRepository repo, CustomerService customerService) {
    this.repo = repo;
    this.customerService = customerService;
  }

  @Override
  public Optional<User> create(User value) {
    Customer cst = new Customer();
    cst.setId(UUID.randomUUID());
    //⚠️
    customerService.create(cst);
    return repo.add(value);
  }

  @Override
  public List<User> all() {
    return repo.getAll();
  }

  @Override
  public Optional<User> get(UUID id) {
    return repo.find(id);
  }

  @Override
  public Optional<User> update(UUID id, User value) {
    return repo.add(value);
  }

  @Override
  public Optional<User> delete(UUID id) {
    return repo.remove(id);
  }
  
}
