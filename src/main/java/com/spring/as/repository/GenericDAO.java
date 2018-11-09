package com.spring.as.repository;

import java.util.List;

public interface GenericDAO<T, U> {

    T read(U id);

    void delete(U id);

    List<T> getAll();

    void update(T t);

    void create(T t);

}
