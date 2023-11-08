package com.javadb.repository;

import java.util.List;
import java.util.Optional;

import com.javadb.bean.User;
import com.javadb.db.Database;

public class UserRepository extends Database implements Repository<User, Integer>{

    @Override
    public Optional<User> add(User value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
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
    public Optional<User> remove(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
  
}
