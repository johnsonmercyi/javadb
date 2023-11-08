package com.javadb.service;

import java.util.List;
import java.util.Optional;

import com.javadb.bean.User;
import com.javadb.contracts.ServiceContract;
import com.javadb.repository.UserRepository;

public class UserService implements ServiceContract<User, Integer> {

    private UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public Optional<User> create(User value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<User> findBy(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public Optional<User> update(Integer id, User value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<User> delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
  
}
