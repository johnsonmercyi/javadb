package com.javadb.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javadb.bean.User;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.UserRepository;

public class UserService implements ServiceContract<User, UUID> {

    private UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public Optional<User> create(User user) {
      return repo.add(user);
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<User> findBy(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public Optional<User> update(UUID id, User value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<User> delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
  
}
