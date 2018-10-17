package com.spring.as.dao;

import java.util.List;

public interface GenericDAO<T> {

    T read(long id);

    void delete(long id);

    List<T> getAll();

    void update(T t);

    void create(T t);

}
