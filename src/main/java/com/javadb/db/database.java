package com.javadb.db;

import java.util.List;
import java.util.Optional;

public abstract class Database<T, K> {
  public Database() {
    //create connection
    createConnection();
  }

  private void createConnection() {

  }

  public void query(String sql) {

  }

  public Optional<T> create(T value) {
    return null;
  }

  public List<T> getAll() {
    return null;
  }

  public Optional<T> find(K id) {
    return null;
  }

  public Optional<T> add(T value) {
    return null;
  }

  public Optional<T> remove(K id) {
    return null;
  }
}
