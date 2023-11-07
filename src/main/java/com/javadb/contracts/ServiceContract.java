package com.javadb.contracts;

import java.util.List;
import java.util.Optional;

public interface ServiceContract<T, K> {
  public Optional<T> create(T value);
  public List<T> getAll(); // read
  public Optional<T> findBy(K id); // read
  public Optional<T> update(K id, T value);
  public Optional<T> delete(K id);
}
