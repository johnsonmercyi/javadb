package com.javadb.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.User;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.UserRepository;

public class UserService implements ServiceContract<User, UUID> {

  private UserRepository repo;

  public UserService(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<User> create(User user) {
    return repo.add(user);
  }

  @Override
  public List<User> getAll() {
    return repo.getAll();
  }

  @Override
  public Optional<User> findBy(UUID id) {
    return repo.findBy(id);
  }

  @Override
  public Optional<User> update(UUID id, User user) {
    User oldUser = repo.findBy(id).get();
    oldUser.setUsername(user.getUsername());
    oldUser.setPassword(user.getPassword());
    oldUser.setEmail(user.getEmail());
    return repo.add(oldUser);
  }

  @Override
  public Optional<User> delete(UUID id) {
    return repo.remove(id);
  }

}
